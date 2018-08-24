package tehseph.netherfoundation.init;

import tehseph.netherfoundation.Reference;
import tehseph.netherfoundation.common.block.*;

import cofh.core.fluid.BlockFluidInteractive;
import cofh.core.util.helpers.StringHelper;
import cofh.thermalfoundation.init.TFFluids;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@ObjectHolder(Reference.MOD_ID)
public class NFBlocks {

    public static final BlockHellfish HELLFISH   = new BlockHellfish();
    public static final BlockNetherOre ORE = new BlockNetherOre();
    public static BlockEndOre END_ORE;
    public static BlockAE2Ore AE2_ORE;
    public static BlockTCOre TC_ORE;
    public static BlockExtraOre EXTRA_ORE;

    public static void preInitCommon() {

        ItemBlock itemBlock;

        if (NFConfig.HELLFISH) {

            HELLFISH.setRegistryName("hellfish");
            ForgeRegistries.BLOCKS.register(HELLFISH);

            itemBlock = new ItemBlock(HELLFISH);
            itemBlock.setRegistryName("hellfish");
            ForgeRegistries.ITEMS.register(itemBlock);

        }

        ORE.setRegistryName("ore");
        ForgeRegistries.BLOCKS.register(ORE);

        itemBlock = new ItemBlockNetherOre(ORE);
        itemBlock.setRegistryName("ore");
        ForgeRegistries.ITEMS.register(itemBlock);

        for (int i = 0; i < BlockNetherOre.Type.values().length; i++) {

            String oreName = BlockNetherOre.Type.byMetadata(i).getName();
            ItemStack oreStack = new ItemStack(ORE, 1, i);

            OreDictionary.registerOre("oreNether" + StringHelper.titleCase(oreName), oreStack);

        }

        IBlockState netherLead = ORE.getDefaultState().withProperty(BlockNetherOre.VARIANT, BlockNetherOre.Type.LEAD);
        IBlockState netherGold = ORE.getDefaultState().withProperty(BlockNetherOre.VARIANT, BlockNetherOre.Type.GOLD);
        ((BlockFluidInteractive) TFFluids.blockFluidMana).addInteraction(netherLead, netherGold);

        IBlockState netherSilver  = ORE.getDefaultState().withProperty(BlockNetherOre.VARIANT, BlockNetherOre.Type.SILVER);
        IBlockState netherMithril = ORE.getDefaultState().withProperty(BlockNetherOre.VARIANT, BlockNetherOre.Type.MITHRIL);
        ((BlockFluidInteractive) TFFluids.blockFluidMana).addInteraction(netherSilver, netherMithril);

        if ( NFConfig.END_ORES ) {
        	END_ORE = new BlockEndOre();
	        END_ORE.setRegistryName("end_ore");
	        ForgeRegistries.BLOCKS.register(END_ORE);
	        
	        itemBlock = new ItemBlockEndOre(END_ORE);
	        itemBlock.setRegistryName("end_ore");
	        ForgeRegistries.ITEMS.register(itemBlock);
	
	        for (int i = 0; i < BlockEndOre.Type.values().length; i++) {
	
	            String oreName = BlockEndOre.Type.byMetadata(i).getName();
	            ItemStack oreStack = new ItemStack(END_ORE, 1, i);
	
	            OreDictionary.registerOre("oreNether" + StringHelper.titleCase(oreName), oreStack); // No reason to have a specific oreEnd* dictionary
	        }
	
	        IBlockState endLead = END_ORE.getDefaultState().withProperty(BlockEndOre.VARIANT, BlockEndOre.Type.LEAD);
	        IBlockState endGold = END_ORE.getDefaultState().withProperty(BlockEndOre.VARIANT, BlockEndOre.Type.GOLD);
	        ((BlockFluidInteractive) TFFluids.blockFluidMana).addInteraction(endLead, endGold);
	
	        IBlockState endSilver  = END_ORE.getDefaultState().withProperty(BlockEndOre.VARIANT, BlockEndOre.Type.SILVER);
	        IBlockState endMithril = END_ORE.getDefaultState().withProperty(BlockEndOre.VARIANT, BlockEndOre.Type.MITHRIL);
	        ((BlockFluidInteractive) TFFluids.blockFluidMana).addInteraction(endSilver, endMithril);
        }
        
        if ( NFConfig.AE2_ORES ) {
        	AE2_ORE = new BlockAE2Ore();
	        AE2_ORE.setRegistryName("ae2_ore");
	        ForgeRegistries.BLOCKS.register(AE2_ORE);
	        
	        itemBlock = new ItemBlockAE2Ore(AE2_ORE);
	        itemBlock.setRegistryName("ae2_ore");
	        ForgeRegistries.ITEMS.register(itemBlock);
        	
	        for (int i = 0; i < BlockAE2Ore.Type.values().length; i++) {
	        	
	            String oreName = BlockAE2Ore.Type.byMetadata(i).getOreName();
	            ItemStack oreStack = new ItemStack(AE2_ORE, 1, i);
	
	            OreDictionary.registerOre("oreNether" + oreName, oreStack); // No reason to have a specific oreEnd* dictionary
	        }
        }
        if ( NFConfig.TC_ORES ) {
        	TC_ORE = new BlockTCOre();
	        TC_ORE.setRegistryName("tc_ore");
	        ForgeRegistries.BLOCKS.register(TC_ORE);
	        
	        itemBlock = new ItemBlockTCOre(TC_ORE);
	        itemBlock.setRegistryName("tc_ore");
	        ForgeRegistries.ITEMS.register(itemBlock);
        	
	        for (int i = 0; i < BlockTCOre.Type.values().length; i++) {
	        	
	            String oreName = BlockTCOre.Type.byMetadata(i).getOreName();
	            ItemStack oreStack = new ItemStack(TC_ORE, 1, i);
	
	            OreDictionary.registerOre("ore" + StringHelper.titleCase(oreName), oreStack);
	        }
        }
        if ( NFConfig.EXTRA_ORES ) {
        	EXTRA_ORE = new BlockExtraOre();
	        EXTRA_ORE.setRegistryName("extra_ore");
	        ForgeRegistries.BLOCKS.register(EXTRA_ORE);
	        
	        itemBlock = new ItemBlockExtraOre(EXTRA_ORE);
	        itemBlock.setRegistryName("extra_ore");
	        ForgeRegistries.ITEMS.register(itemBlock);
        	
	        for (int i = 0; i < BlockExtraOre.Type.values().length; i++) {
	        	
	            String oreName = BlockExtraOre.Type.byMetadata(i).getOreName();
	            ItemStack oreStack = new ItemStack(EXTRA_ORE, 1, i);
	
	            OreDictionary.registerOre("ore" + StringHelper.titleCase(oreName), oreStack);
	        }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void preInitClient() {

        Item item;
        ModelResourceLocation model;

        if (NFConfig.HELLFISH) {

            item = Item.getItemFromBlock(HELLFISH);
            model = new ModelResourceLocation(HELLFISH.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, 0, model);

        }

        for (int i = 0; i < BlockNetherOre.Type.values().length; i++) {

            item = Item.getItemFromBlock(ORE);
            model = new ModelResourceLocation(ORE.getRegistryName(), "type=" + BlockNetherOre.Type.byMetadata(i).getName());
            ModelLoader.setCustomModelResourceLocation(item, i, model);

        }

        if ( NFConfig.END_ORES ) {
	        for (int i = 0; i < BlockEndOre.Type.values().length; i++) {
	
	            item = Item.getItemFromBlock(END_ORE);
	            model = new ModelResourceLocation(END_ORE.getRegistryName(), "type=" + BlockEndOre.Type.byMetadata(i).getName());
	            ModelLoader.setCustomModelResourceLocation(item, i, model);
	        }
        }

        if ( NFConfig.AE2_ORES ) {
	        for (int i = 0; i < BlockAE2Ore.Type.values().length; i++) {
	            item = Item.getItemFromBlock(AE2_ORE);
	            model = new ModelResourceLocation(AE2_ORE.getRegistryName(), "type=" + BlockAE2Ore.Type.byMetadata(i).getName());
	            ModelLoader.setCustomModelResourceLocation(item, i, model);
	        }
        }

        if ( NFConfig.TC_ORES ) {
	        for (int i = 0; i < BlockTCOre.Type.values().length; i++) {
	            item = Item.getItemFromBlock(TC_ORE);
	            model = new ModelResourceLocation(TC_ORE.getRegistryName(), "type=" + BlockTCOre.Type.byMetadata(i).getName());
	            ModelLoader.setCustomModelResourceLocation(item, i, model);
	        }
        }
        
        if ( NFConfig.EXTRA_ORES ) {
	        for (int i = 0; i < BlockExtraOre.Type.values().length; i++) {
	            item = Item.getItemFromBlock(EXTRA_ORE);
	            model = new ModelResourceLocation(EXTRA_ORE.getRegistryName(), "type=" + BlockExtraOre.Type.byMetadata(i).getName());
	            ModelLoader.setCustomModelResourceLocation(item, i, model);
	        }
        }

    }
}
