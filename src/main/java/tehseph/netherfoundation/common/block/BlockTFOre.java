package tehseph.netherfoundation.common.block;

import tehseph.netherfoundation.Reference;
import cofh.core.block.BlockCore;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockTFOre extends BlockCore {

    public static final PropertyEnum<Type> VARIANT = PropertyEnum.create("type", Type.class);

    public BlockTFOre() {

        super(Material.ROCK, "netherfoundation");

        this.setCreativeTab(Reference.CREATIVE_TAB);
        this.setUnlocalizedName("tf_ore");

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Type.COPPER));

        this.setSoundType(SoundType.STONE);
        this.setHardness(3.0F);
        this.setResistance(5.0F);

        this.setHarvestLevel("pickaxe", 3);
		setHarvestLevel("pickaxe", 2, getStateFromMeta(Type.COPPER.getMetadata()));
		setHarvestLevel("pickaxe", 2, getStateFromMeta(Type.TIN.getMetadata()));
		setHarvestLevel("pickaxe", 2, getStateFromMeta(Type.ALUMINUM.getMetadata()));
		setHarvestLevel("pickaxe", 4, getStateFromMeta(Type.PLATINUM.getMetadata()));
		setHarvestLevel("pickaxe", 4, getStateFromMeta(Type.IRIDIUM.getMetadata()));
		setHarvestLevel("pickaxe", 4, getStateFromMeta(Type.MITHRIL.getMetadata()));

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

        return meta;

    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {

        return this.quantityDroppedWithBonus(fortune, random);

    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {

        return Item.getItemFromBlock(this);

    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this, 1, state.getValue(VARIANT).getMetadata());
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(this, 1, state.getValue(VARIANT).getMetadata());
    }

	public enum Type implements IStringSerializable {

        // TYPE  (META, NAME,       LIGHT, RARITY)
        COPPER   (0,    "copper",   0,     EnumRarity.COMMON),
		TIN      (1,    "tin",      0,     EnumRarity.COMMON),
		SILVER   (2,    "silver",   4,     EnumRarity.COMMON),
		LEAD     (3,    "lead",     0,     EnumRarity.COMMON),
		ALUMINUM (4,    "aluminum", 0,     EnumRarity.COMMON),
		NICKEL   (5,    "nickel",   0,     EnumRarity.COMMON),
		PLATINUM (6,    "platinum", 4,     EnumRarity.UNCOMMON),
		IRIDIUM  (7,    "iridium",  4,     EnumRarity.UNCOMMON),
        MITHRIL  (8,    "mithril",  8,     EnumRarity.RARE);

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
