package tehseph.netherfoundation.common.block;

import java.util.Optional;
import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import appeng.api.AEApi;
import appeng.api.definitions.IMaterials; 
import cofh.core.block.BlockCore;
import tehseph.netherfoundation.Reference;
import tehseph.netherfoundation.client.renderer.effects.ChargedOreFX;

@SuppressWarnings("deprecation")
public class BlockAE2Ore extends BlockCore {

    public static final PropertyEnum<Type> VARIANT = PropertyEnum.create("type", Type.class);

    private static Optional<ItemStack> certusQuartzCrystal;
    private static Optional<ItemStack> certusQuartzCrystalCharged;
    
    public BlockAE2Ore() {

        super(Material.ROCK, "netherfoundation");

        this.setCreativeTab(Reference.CREATIVE_TAB);
        this.setUnlocalizedName("ae2_ore");

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Type.CERTUS));

        this.setSoundType(SoundType.STONE);
        this.setHardness(3.0F);
        this.setResistance(5.0F);

        this.setHarvestLevel("pickaxe", 2);

        IMaterials materialsApi = AEApi.instance().definitions().materials();
        certusQuartzCrystal = materialsApi.certusQuartzCrystal().maybeStack(1);
        certusQuartzCrystalCharged = materialsApi.certusQuartzCrystalCharged().maybeStack(1);
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

        if ((meta ==  0 || meta == 2) && certusQuartzCrystal.isPresent())  return certusQuartzCrystal.get().getMetadata(); /* Certus Quartz */     
        if ((meta ==  1 || meta == 3) && certusQuartzCrystalCharged.isPresent())  return certusQuartzCrystalCharged.get().getMetadata(); /* Charged Certus Quartz */     

        return meta;

    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {

        int meta = state.getValue(VARIANT).getMetadata();
        int bonus = fortune > 0 ? random.nextInt(fortune + 1) + 1 : 1;

       if (meta <= 3)  return 2 * bonus;  /* Certus Quartz */     

        return this.quantityDroppedWithBonus(fortune, random);

    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {

        int meta = state.getValue(VARIANT).getMetadata();

        if ((meta ==  0 || meta == 2) && certusQuartzCrystal.isPresent())  return certusQuartzCrystal.get().getItem(); /* Certus Quartz */     
        if ((meta ==  1 || meta == 3) && certusQuartzCrystalCharged.isPresent())  return certusQuartzCrystalCharged.get().getItem(); /* Charged Certus Quartz */     

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

    @Override
	@SideOnly( Side.CLIENT )
	public void randomDisplayTick( final IBlockState state, final World w, final BlockPos pos, final Random r )
	{
        int meta = state.getValue(VARIANT).getMetadata();
        if (meta !=  1 && meta != 3) return;     

        double xOff = ( r.nextFloat() );
		double yOff = ( r.nextFloat() );
		double zOff = ( r.nextFloat() );

		switch( r.nextInt( 6 ) )
		{
			case 0:
				xOff = -0.01;
				break;
			case 1:
				yOff = -0.01;
				break;
			case 2:
				xOff = -0.01;
				break;
			case 3:
				zOff = -0.01;
				break;
			case 4:
				xOff = 1.01;
				break;
			case 5:
				yOff = 1.01;
				break;
			case 6:
				zOff = 1.01;
				break;
		}

		final ChargedOreFX fx = new ChargedOreFX( w, pos.getX() + xOff, pos.getY() + yOff, pos.getZ() + zOff, 0.0f, 0.0f, 0.0f );
		Minecraft.getMinecraft().effectRenderer.addEffect( fx );
	}
    
    
	public enum Type implements IStringSerializable {

        // TYPE  			(META, NAME,       				ORENAME,				LIGHT, RARITY)
        CERTUS				(0,    "certus",				"CertusQuartz", 		0,     EnumRarity.COMMON),
        CHARGED_CERTUS		(1,    "charged_certus",		"ChargedCertusQuartz",	2,     EnumRarity.COMMON),
        END_CERTUS			(2,    "end_certus",			"CertusQuartz",			0,     EnumRarity.COMMON),
        END_CHARGED_CERTUS	(3,    "end_charged_certus",	"ChargedCertusQuartz",	2,     EnumRarity.COMMON);

		private static final Type[] METADATA_LOOKUP = new Type[values().length];

		private final int metadata;
		private final String name;
		private final String oreName;
		private final int light;
		private final EnumRarity rarity;

		Type(int metadata, String name, String oreName, int light, EnumRarity rarity) {

			this.metadata = metadata;
			this.name = name;
			this.oreName = oreName;
			this.light = light;
			this.rarity = rarity;

		}

        @Override
        public String getName() {
		    return this.name;
		}

        public String getOreName() {
		    return this.oreName;
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
