package tehseph.netherfoundation.entity.monster;

import tehseph.netherfoundation.Reference;
import tehseph.netherfoundation.init.NFBlocks;
import tehseph.netherfoundation.init.NFConfig;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.Random;

public class EntityHellfish extends EntitySilverfish {

    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(Reference.MOD_ID, "entities/hellfish");
    private EntityHellfish.AISummonHellfish summonHellfish;

    public EntityHellfish(World worldIn) {

        super(worldIn);
        this.isImmuneToFire = true;

    }

    @Override
    protected void applyEntityAttributes() {

        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(NFConfig.HELLFISH_MAX_HEALTH);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(NFConfig.HELLFISH_MOVEMENT_SPEED);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(NFConfig.HELLFISH_ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(NFConfig.HELLFISH_KNOCKBACK_RESISTANCE);

    }

    @Override
    @SuppressWarnings("unchecked")
    protected void initEntityAI() {

        this.summonHellfish = new EntityHellfish.AISummonHellfish(this);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, this.summonHellfish);
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityHellfish.AIHideInNetherrack(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

    }

    @Override
    public void onLivingUpdate() {

        if (world.isRemote) {

            double x = this.posX + (this.rand.nextDouble() - 0.5D) * this.width;
            double y = this.posY + this.rand.nextDouble() * this.height;
            double z = this.posZ + (this.rand.nextDouble() - 0.5D) * this.width;

            this.world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);

        }

        super.onLivingUpdate();

    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {

        if (this.isEntityInvulnerable(source)) return false;

        if ((source instanceof EntityDamageSource || source == DamageSource.MAGIC) && this.summonHellfish != null) this.summonHellfish.notifyHurt();

        return super.attackEntityFrom(source, amount);

    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (NFConfig.HELLFISH_SET_FIRE) entityIn.setFire(3);
        return super.attackEntityAsMob(entityIn);
    }

    @Override
    protected ResourceLocation getLootTable() {
        return LOOT_TABLE;
    }

    static class AISummonHellfish extends EntityAIBase {

        private final EntityHellfish hellfish;
        private int lookForFriends;

        public AISummonHellfish(EntityHellfish hellfish) {
            this.hellfish = hellfish;
        }

        public void notifyHurt() {
            if (this.lookForFriends == 0) this.lookForFriends = 40;
        }

        @Override
        public boolean shouldExecute() {
            return this.lookForFriends > 0;
        }

        @Override
        public void updateTask() {

            --this.lookForFriends;

            if (this.lookForFriends <= 0) {

                World world = this.hellfish.world;
                Random random = this.hellfish.getRNG();
                BlockPos blockpos = new BlockPos(this.hellfish);

                for (int y = 0; y <= 5 && y >= -5; y = (y <= 0 ? 1 : 0) - y) {
                    for (int x = 0; x <= 10 && x >= -10; x = (x <= 0 ? 1 : 0) - x) {
                        for (int z = 0; z <= 10 && z >= -10; z = (z <= 0 ? 1 : 0) - z) {

                            BlockPos friendBlock = blockpos.add(x, y, z);
                            IBlockState iblockstate = world.getBlockState(friendBlock);

                            if (iblockstate.getBlock() == NFBlocks.HELLFISH) {

                                if (ForgeEventFactory.getMobGriefingEvent(world, this.hellfish)) world.destroyBlock(friendBlock, true);
                                else world.setBlockState(friendBlock, Blocks.NETHERRACK.getDefaultState(), 3);

                                if (random.nextBoolean()) return;

                            }

                        }
                    }
                }

            }

        }

    }

    static class AIHideInNetherrack extends EntityAIWander {

        private EnumFacing facing;
        private boolean doMerge;

        public AIHideInNetherrack(EntityHellfish hellfish) {
            super(hellfish, 1.0D, 10);
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute() {

            if (this.entity.getAttackTarget() != null) return false;

            else if (!this.entity.getNavigator().noPath()) return false;

            else {

                World world = this.entity.world;
                Random random = this.entity.getRNG();

                if (ForgeEventFactory.getMobGriefingEvent(world, this.entity) && random.nextInt(10) == 0) {

                    this.facing = EnumFacing.random(random);

                    BlockPos blockPos = (new BlockPos(this.entity.posX, this.entity.posY + 0.5D, this.entity.posZ)).offset(this.facing);
                    IBlockState blockState = world.getBlockState(blockPos);

                    if (blockState == Blocks.NETHERRACK.getDefaultState()) {
                        this.doMerge = true;
                        return true;
                    }

                }

                this.doMerge = false;
                return super.shouldExecute();

            }

        }

        @Override
        public boolean shouldContinueExecuting() {
            return !this.doMerge && super.shouldContinueExecuting();
        }

        @Override
        public void startExecuting() {

            if (!this.doMerge) super.startExecuting();

            else {

                World world = this.entity.world;

                BlockPos blockPos = (new BlockPos(this.entity.posX, this.entity.posY + 0.5D, this.entity.posZ)).offset(this.facing);
                IBlockState blockState = world.getBlockState(blockPos);

                if (blockState == Blocks.NETHERRACK.getDefaultState()) {

                    world.setBlockState(blockPos, NFBlocks.HELLFISH.getDefaultState(), 3);

                    this.entity.spawnExplosionParticle();
                    this.entity.setDead();

                }

            }

        }

    }

}
