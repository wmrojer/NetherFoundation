package tehseph.netherfoundation.common.block;

import tehseph.netherfoundation.Reference;
import tehseph.netherfoundation.entity.monster.EntityHellfish;

import cofh.core.block.BlockCore;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockHellfish extends BlockCore {

    public BlockHellfish() {

        super(Material.ROCK, "netherfoundation");

        this.setCreativeTab(Reference.CREATIVE_TAB);
        this.setUnlocalizedName("hellfish");

        this.setSoundType(SoundType.STONE);
        this.setHardness(0.1F);

    }

    @Override
    public boolean isFireSource(World world, BlockPos blockPos, EnumFacing facing) {
        return facing == EnumFacing.UP;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.NETHERRACK;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(Blocks.NETHERRACK, 1, 0);
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {

        if (!worldIn.isRemote && worldIn.getGameRules().getBoolean("doTileDrops")) {

            EntityHellfish hellfish = new EntityHellfish(worldIn);
            hellfish.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);

            worldIn.spawnEntity(hellfish);

            hellfish.spawnExplosionParticle();

        }

    }

}
