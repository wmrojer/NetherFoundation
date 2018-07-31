package tehseph.netherfoundation.common.block;

import tehseph.netherfoundation.Reference;

import cofh.core.block.BlockCore;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
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
public class BlockNetherOre extends BlockCore {

    public static final PropertyEnum<Type> VARIANT = PropertyEnum.create("type", Type.class);

    public BlockNetherOre() {

        super(Material.ROCK, "netherfoundation");

        this.setCreativeTab(Reference.CREATIVE_TAB);
        this.setUnlocalizedName("ore");

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Type.GOLD));

        this.setSoundType(SoundType.STONE);
        this.setHardness(3.0F);
        this.setResistance(5.0F);

        this.setHarvestLevel("pickaxe", 3);

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

        /* Coal */     if (meta == 2)  return 0;
        /* Lapis */    if (meta == 3)  return EnumDyeColor.BLUE.getDyeDamage();
        /* Diamond */  if (meta == 4)  return 0;
        /* Redstone */ if (meta == 5)  return 0;
        /* Emerald */  if (meta == 15) return 0;

        return meta;

    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {

        int meta = state.getValue(VARIANT).getMetadata();
        int bonus = fortune > 0 ? random.nextInt(fortune + 1) + 1 : 1;

        /* Coal */     if (meta == 2)  return 2 * bonus;
        /* Lapis */    if (meta == 3)  return 4 + random.nextInt(5) * bonus;
        /* Diamond */  if (meta == 4)  return 2 * bonus;
        /* Redstone */ if (meta == 5)  return 4 + random.nextInt(2) * bonus;
        /* Emerald */  if (meta == 15) return 2 * bonus;

        return this.quantityDroppedWithBonus(fortune, random);

    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {

        int meta = state.getValue(VARIANT).getMetadata();

        /* Coal */     if (meta == 2)  return Items.COAL;
        /* Lapis */    if (meta == 3)  return Items.DYE;
        /* Diamond */  if (meta == 4)  return Items.DIAMOND;
        /* Redstone */ if (meta == 5)  return Items.REDSTONE;
        /* Emerald */  if (meta == 15) return Items.EMERALD;

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
        GOLD     (0,    "gold",     0,     EnumRarity.COMMON),
        IRON     (1,    "iron",     0,     EnumRarity.COMMON),
        COAL     (2,    "coal",     0,     EnumRarity.COMMON),
        LAPIS    (3,    "lapis",    0,     EnumRarity.COMMON),
        DIAMOND  (4,    "diamond",  1,     EnumRarity.COMMON),
        REDSTONE (5,    "redstone", 4,     EnumRarity.COMMON),
        COPPER   (6,    "copper",   0,     EnumRarity.COMMON),
		TIN      (7,    "tin",      0,     EnumRarity.COMMON),
		SILVER   (8,    "silver",   4,     EnumRarity.COMMON),
		LEAD     (9,    "lead",     0,     EnumRarity.COMMON),
		ALUMINUM (10,   "aluminum", 0,     EnumRarity.COMMON),
		NICKEL   (11,   "nickel",   0,     EnumRarity.COMMON),
		PLATINUM (12,   "platinum", 4,     EnumRarity.UNCOMMON),
		IRIDIUM  (13,   "iridium",  4,     EnumRarity.UNCOMMON),
        MITHRIL  (14,   "mithril",  8,     EnumRarity.RARE),
        EMERALD  (15,   "emerald",  0,     EnumRarity.COMMON);

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
