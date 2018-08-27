package tehseph.netherfoundation.proxy;

import tehseph.netherfoundation.init.*;
import cofh.thermalfoundation.init.TFBlocks;
import slimeknights.tconstruct.shared.TinkerCommons;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {

        NFConfig.preInitCommon(event.getSuggestedConfigurationFile());
        NFBlocks.preInitCommon();
        NFEntities.preInitCommon();

        NFCompat.preInitCommon();

        MinecraftForge.EVENT_BUS.register(this);

    }

    public void init(FMLInitializationEvent event) {

        NFRecipes.initCommon();

    }

    public void postInit(FMLPostInitializationEvent event) {

        NFConfig.postInitCommon();

    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {

    	if (!NFConfig.ANGRY_PIGMEN && !NFConfig.EXPLOSIONS) return; // Fast exit
    	
        boolean silktouch = hasEnchant(event.getPlayer(), Enchantments.SILK_TOUCH);

        World        world      = event.getWorld();
        BlockPos     blockPos   = event.getPos();
        IBlockState  blockState = event.getState();
        EntityPlayer player     = event.getPlayer();

        if ( isNetherOre(blockState) ) {
	        if (NFConfig.ANGRY_PIGMEN) {
	            if (!(silktouch && NFConfig.ANGRY_PIGMEN_SILKTOUCH)) angerPigmen(world, blockPos, player);
	        }
	
	        if (NFConfig.EXPLOSIONS && !player.isCreative()) {
	
	            boolean fortune   = hasEnchant(event.getPlayer(), Enchantments.FORTUNE);
	            int multi = (NFConfig.EXPLOSIONS_FORTUNE && fortune) ? 2 : 1;
	
	            if (!(silktouch && NFConfig.EXPLOSIONS_SILKTOUCH)) {
	                if (world.rand.nextDouble() <= NFConfig.EXPLOSIONS_CHANCE * multi) {
	                    world.createExplosion(player, blockPos.getX(), blockPos.getY(), blockPos.getZ(), (float) NFConfig.EXPLOSIONS_STRENGTH, true);
	                }
	            }
	        }
        }

    }

    private void angerPigmen(World world, BlockPos blockPos, EntityPlayer player) {

        BlockPos start = new BlockPos(blockPos).add(-NFConfig.ANGRY_PIGMEN_RANGE, -NFConfig.ANGRY_PIGMEN_RANGE, -NFConfig.ANGRY_PIGMEN_RANGE);
        BlockPos end   = new BlockPos(blockPos).add(+NFConfig.ANGRY_PIGMEN_RANGE, +NFConfig.ANGRY_PIGMEN_RANGE, +NFConfig.ANGRY_PIGMEN_RANGE);

        AxisAlignedBB aabb  = new AxisAlignedBB(start, end);

        List<EntityPigZombie> list = world.getEntitiesWithinAABB(EntityPigZombie.class, aabb);
        for (EntityPigZombie pigman : list) pigman.setRevengeTarget(player);

    }

    private boolean hasEnchant(EntityPlayer player, Enchantment enchant) {

        if (player == null || player.getHeldItemMainhand().isEmpty()) return false;

        NBTTagList list = player.getHeldItemMainhand().getEnchantmentTagList();

        for (int i = 0; i < list.tagCount(); i++) {
            short enchantId = list.getCompoundTagAt(i).getShort("id");
            if (Enchantment.getEnchantmentByID(enchantId) == enchant) return true;
        }

        return false;

    }

    private boolean isNetherOre(IBlockState blockState) {

        if (Loader.isModLoaded("tconstruct")) {
            if (blockState.getBlock() == TinkerCommons.blockOre) return true;
        }

        if (blockState == TFBlocks.blockOreFluid.getStateFromMeta(3)) return true;
//        if (blockState.getBlock() == NFBlocks.ORE) return true;
        if (blockState.getBlock() == NFBlocks.VANILLA_ORE) return true;
        if (blockState.getBlock() == NFBlocks.TF_ORE) return true;
//        if (NFConfig.END_ORES && blockState.getBlock() == NFBlocks.END_ORE) return true;
        if (NFConfig.END_ORES && blockState.getBlock() == NFBlocks.VANILLA_END_ORE) return true;
        if (NFConfig.END_ORES && blockState.getBlock() == NFBlocks.TF_END_ORE) return true;
        if (NFConfig.AE2_ORES && blockState.getBlock() == NFBlocks.AE2_ORE) return true;
        if (blockState.getBlock() == Blocks.QUARTZ_ORE) return true;

        return false;

    }

}
