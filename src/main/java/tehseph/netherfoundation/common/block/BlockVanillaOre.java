package tehseph.netherfoundation.common.block;

import tehseph.netherfoundation.Reference;
import cofh.core.block.BlockCore;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockVanillaOre extends BlockCore {

    public static final PropertyEnum<Type> VARIANT = PropertyEnum.create("type", Type.class);

    public BlockVanillaOre() {

        super(Material.ROCK, "netherfoundation");

        this.setCreativeTab(Reference.CREATIVE_TAB);
        this.setUnlocalizedName("vanilla_ore");

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Type.GOLD));

        this.setSoundType(SoundType.STONE);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setTickRandomly(true);
        
        this.setHarvestLevel("pickaxe", 3);
		setHarvestLevel("pickaxe", 2, getStateFromMeta(Type.COAL.getMetadata()));
		setHarvestLevel("pickaxe", 2, getStateFromMeta(Type.IRON.getMetadata()));
		setHarvestLevel("pickaxe", 4, getStateFromMeta(Type.GOLD.getMetadata()));
		setHarvestLevel("pickaxe", 4, getStateFromMeta(Type.DIAMOND.getMetadata()));
		setHarvestLevel("pickaxe", 4, getStateFromMeta(Type.EMERALD.getMetadata()));

    }

    /**
     * How many world ticks before ticking
     */
    @Override
    public int tickRate(World worldIn)
    {
        return 30;
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
    {
    	IBlockState state = worldIn.getBlockState(pos);
        this.activate(worldIn, pos, state);
        super.onBlockClicked(worldIn, pos, playerIn);
    }

    /**
     * Called when the given entity walks on this Block
     */
    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
    	IBlockState state = worldIn.getBlockState(pos);
        this.activate(worldIn, pos, state);
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    /**
     * Called when the block is right clicked by a player.
     */
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        this.activate(worldIn, pos, state);
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    private void activate(World worldIn, BlockPos pos, IBlockState state)
    {
    	int meta = state.getValue(VARIANT).getMetadata();
        if (meta == 5) // Unlit Redstone
        {
        	this.spawnParticles(worldIn, pos);
            worldIn.setBlockState(pos, this.blockState.getBaseState().withProperty(VARIANT, Type.LIT_REDSTONE));
        }
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
    	int meta = state.getValue(VARIANT).getMetadata();
        if (meta == 6)  // Lit Redstone
        {
            worldIn.setBlockState(pos, this.blockState.getBaseState().withProperty(VARIANT, Type.REDSTONE));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
    	int meta = stateIn.getValue(VARIANT).getMetadata();
        if (meta == 6) // Lit Redstone
        {
            this.spawnParticles(worldIn, pos);
        }
    }

    private void spawnParticles(World worldIn, BlockPos pos)
    {
        Random random = worldIn.rand;
        double d0 = 0.0625D;

        for (int i = 0; i < 6; ++i)
        {
            double d1 = (double)((float)pos.getX() + random.nextFloat());
            double d2 = (double)((float)pos.getY() + random.nextFloat());
            double d3 = (double)((float)pos.getZ() + random.nextFloat());

            if (i == 0 && !worldIn.getBlockState(pos.up()).isOpaqueCube())
            {
                d2 = (double)pos.getY() + d0 + 1.0D;
            }

            if (i == 1 && !worldIn.getBlockState(pos.down()).isOpaqueCube())
            {
                d2 = (double)pos.getY() - d0;
            }

            if (i == 2 && !worldIn.getBlockState(pos.south()).isOpaqueCube())
            {
                d3 = (double)pos.getZ() + d0 + 1.0D;
            }

            if (i == 3 && !worldIn.getBlockState(pos.north()).isOpaqueCube())
            {
                d3 = (double)pos.getZ() - d0;
            }

            if (i == 4 && !worldIn.getBlockState(pos.east()).isOpaqueCube())
            {
                d1 = (double)pos.getX() + d0 + 1.0D;
            }

            if (i == 5 && !worldIn.getBlockState(pos.west()).isOpaqueCube())
            {
                d1 = (double)pos.getX() - d0;
            }

            if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0D || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1))
            {
                worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d1, d2, d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }
    
    @Override
    public boolean isFireSource(World world, BlockPos blockPos, EnumFacing facing) {
        return facing == EnumFacing.UP;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < Type.values().length; i++) {
        	if ( i != 6 ) // Don't include lit redstone
        		items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, Type.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state.getValue(VARIANT).getLight();
    }

    @Override
    public int damageDropped(IBlockState state) {

        int meta = state.getValue(VARIANT).getMetadata();

        /* Coal */     if (meta == 2)  return 0;
        /* Lapis */    if (meta == 3)  return EnumDyeColor.BLUE.getDyeDamage();
        /* Diamond */  if (meta == 4)  return 0;
        /* Redstone */ if (meta == 5 || meta == 6)  return 0;
        /* Emerald */  if (meta == 7)  return 0;
        /* Quartz */   if (meta == 8)  return 0;
        
        return meta;

    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {

        int meta = state.getValue(VARIANT).getMetadata();
        int bonus = fortune > 0 ? random.nextInt(fortune + 1) + 1 : 1;

        /* Coal */     if (meta == 2)  return 2 * bonus;
        /* Lapis */    if (meta == 3)  return 4 + random.nextInt(5) * bonus;
        /* Diamond */  if (meta == 4)  return 2 * bonus;
        /* Redstone */ if (meta == 5 || meta == 6)  return 4 + random.nextInt(2) * bonus;
        /* Emerald */  if (meta == 7)  return 2 * bonus;
        /* End Quartz */ if (meta == 8)  return 2 * bonus;
        
        return this.quantityDroppedWithBonus(fortune, random);

    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {

        int meta = state.getValue(VARIANT).getMetadata();

        /* Coal */     if (meta == 2)  return Items.COAL;
        /* Lapis */    if (meta == 3)  return Items.DYE;
        /* Diamond */  if (meta == 4)  return Items.DIAMOND;
        /* Redstone */ if (meta == 5 || meta == 6)  return Items.REDSTONE;
        /* Emerald */  if (meta == 7)  return Items.EMERALD;
        /* Quartz */   if (meta == 8)  return Items.QUARTZ;
        
        return Item.getItemFromBlock(this);

    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        int meta = state.getValue(VARIANT).getMetadata();
        if (meta == 6) meta = 5; // Lit Redstone drops Normal Redstone
        return new ItemStack(this, 1, meta);
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        int meta = state.getValue(VARIANT).getMetadata();
        if (meta == 6) meta = 5; // Lit Redstone drops Normal Redstone
        return new ItemStack(this, 1, meta);
    }

	public enum Type implements IStringSerializable {

        // TYPE  (META, NAME,       LIGHT, RARITY)
        GOLD     (0,    "gold",     0,     EnumRarity.COMMON),
        IRON     (1,    "iron",     0,     EnumRarity.COMMON),
        COAL     (2,    "coal",     0,     EnumRarity.COMMON),
        LAPIS    (3,    "lapis",    0,     EnumRarity.COMMON),
        DIAMOND  (4,    "diamond",  1,     EnumRarity.COMMON),
        REDSTONE (5,    "redstone", 0,     EnumRarity.COMMON),
        LIT_REDSTONE (6, "lit_redstone", 9, EnumRarity.COMMON),
        EMERALD  (7,    "emerald",  0,     EnumRarity.COMMON);

		private static final Type[] METADATA_LOOKUP = new Type[values().length];

		private final int metadata;
		private final String name;
		private final int light;
		private final EnumRarity rarity;

		Type(int metadata, String name, int light, EnumRarity rarity) {

			this.metadata = metadata;
			this.name = name;
			this.light = light;
			this.rarity = rarity;

		}

        @Override
        public String getName() {
		    return this.name;
		}

		public int getMetadata() {
		    return this.metadata;
		}

		public int getLight() {
		    return this.light;
		}

		public EnumRarity getRarity() {
		    return this.rarity;
		}

		public static Type byMetadata(int metadata) {
			if (metadata < 0 || metadata >= METADATA_LOOKUP.length) metadata = 0;
			return METADATA_LOOKUP[metadata];
		}

		static {
			for (Type type : values()) {
				METADATA_LOOKUP[type.getMetadata()] = type;
			}
		}

	}

}
