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
    public static BlockNetherOre ORE;  // Old Version
    public static final BlockVanillaOre VANILLA_ORE = new BlockVanillaOre();
    public static final BlockTFOre TF_ORE = new BlockTFOre();
    public static BlockEndOre END_ORE;  // Old Version
    public static BlockVanillaEndOre VANILLA_END_ORE;
    public static BlockTFEndOre TF_END_ORE;
    public static BlockAE2Ore AE2_ORE;
    public static BlockTCOre TC_ORE;

    public static void preInitCommon() {

        ItemBlock itemBlock;

        if (NFConfig.HELLFISH) {

            HELLFISH.setRegistryName("hellfish");
            ForgeRegistries.BLOCKS.register(HELLFISH);

            itemBlock = new ItemBlock(HELLFISH);
            itemBlock.setRegistryName("hellfish");
            ForgeRegistries.ITEMS.register(itemBlock);

        }

        // Old Nether Block
        if (NFConfig.OLD_NETHER_ORES) {
        	ORE = new BlockNetherOre();
            ORE.setRegistryName("ore");
            ForgeRegistries.BLOCKS.register(ORE);

            itemBlock = new ItemBlockNetherOre(ORE);
            itemBlock.setRegistryName("ore");
            ForgeRegistries.ITEMS.register(itemBlock);
/*
            for (int i = 0; i < BlockNetherOre.Type.values().length; i++) {
                String oreName = BlockNetherOre.Type.byMetadata(i).getName();
                ItemStack oreStack = new ItemStack(ORE, 1, i);
                OreDictionary.registerOre("oreNether" + StringHelper.titleCase(oreName), oreStack);
            }
*/
        }


        // New Nether Block for vanilla ores.
        VANILLA_ORE.setRegistryName("vanilla_ore");
        ForgeRegistries.BLOCKS.register(VANILLA_ORE);

        itemBlock = new ItemBlockVanillaOre(VANILLA_ORE);
        itemBlock.setRegistryName("vanilla_ore");
        ForgeRegistries.ITEMS.register(itemBlock);

        for (int i = 0; i < BlockVanillaOre.Type.values().length; i++) {

            String oreName = BlockVanillaOre.Type.byMetadata(i).getName();
            ItemStack oreStack = new ItemStack(VANILLA_ORE, 1, i);

            OreDictionary.registerOre("oreNether" + StringHelper.titleCase(oreName), oreStack);
        }

        // New Nether Block for TF ores.
        TF_ORE.setRegistryName("tf_ore");
        ForgeRegistries.BLOCKS.register(TF_ORE);

        itemBlock = new ItemBlockTFOre(TF_ORE);
        itemBlock.setRegistryName("tf_ore");
        ForgeRegistries.ITEMS.register(itemBlock);

        for (int i = 0; i < BlockTFOre.Type.values().length; i++) {

            String oreName = BlockTFOre.Type.byMetadata(i).getName();
            ItemStack oreStack = new ItemStack(TF_ORE, 1, i);

            OreDictionary.registerOre("oreNether" + StringHelper.titleCase(oreName), oreStack);
        }

        // Register Thermal Foundation Fluid Mana effect
        IBlockState netherLead = TF_ORE.getDefaultState().withProperty(BlockTFOre.VARIANT, BlockTFOre.Type.LEAD);
        IBlockState netherGold = VANILLA_ORE.getDefaultState().withProperty(BlockVanillaOre.VARIANT, BlockVanillaOre.Type.GOLD);
        ((BlockFluidInteractive) TFFluids.blockFluidMana).addInteraction(netherLead, netherGold);

        IBlockState netherSilver  = TF_ORE.getDefaultState().withProperty(BlockTFOre.VARIANT, BlockTFOre.Type.SILVER);
        IBlockState netherMithril = TF_ORE.getDefaultState().withProperty(BlockTFOre.VARIANT, BlockTFOre.Type.MITHRIL);
        ((BlockFluidInteractive) TFFluids.blockFluidMana).addInteraction(netherSilver, netherMithril);
        

        if ( NFConfig.END_ORES ) {

        	// Old End Block
        	if (NFConfig.OLD_END_ORES) {
	        	END_ORE = new BlockEndOre();
		        END_ORE.setRegistryName("end_ore");
		        ForgeRegistries.BLOCKS.register(END_ORE);
		        
		        itemBlock = new ItemBlockEndOre(END_ORE);
		        itemBlock.setRegistryName("end_ore");
		        ForgeRegistries.ITEMS.register(itemBlock);
	/*
		        for (int i = 0; i < BlockEndOre.Type.values().length; i++) {
		            String oreName = BlockEndOre.Type.byMetadata(i).getName();
		            ItemStack oreStack = new ItemStack(END_ORE, 1, i);
		            OreDictionary.registerOre("oreNether" + StringHelper.titleCase(oreName), oreStack);
		        }
	*/
        	}
        	
	        // New End Block for Vanilla Ores
        	VANILLA_END_ORE = new BlockVanillaEndOre();
        	VANILLA_END_ORE.setRegistryName("vanilla_end_ore");
	        ForgeRegistries.BLOCKS.register(VANILLA_END_ORE);
	        
	        itemBlock = new ItemBlockVanillaEndOre(VANILLA_END_ORE);
	        itemBlock.setRegistryName("vanilla_end_ore");
	        ForgeRegistries.ITEMS.register(itemBlock);
	
	        for (int i = 0; i < BlockVanillaEndOre.Type.values().length; i++) {
	
	            String oreName = BlockVanillaEndOre.Type.byMetadata(i).getName();
	            ItemStack oreStack = new ItemStack(VANILLA_END_ORE, 1, i);
	
	            if (i == 8) // Reqister End Quartz as oreQuartz
		            OreDictionary.registerOre("ore" + StringHelper.titleCase(oreName), oreStack); 
	            else
	            	OreDictionary.registerOre("oreNether" + StringHelper.titleCase(oreName), oreStack); // No reason to have a specific oreEnd* dictionary
	        }
	
	        // New End Block for TF Ores
        	TF_END_ORE = new BlockTFEndOre();
        	TF_END_ORE.setRegistryName("tf_end_ore");
	        ForgeRegistries.BLOCKS.register(TF_END_ORE);
	        
	        itemBlock = new ItemBlockTFEndOre(TF_END_ORE);
	        itemBlock.setRegistryName("tf_end_ore");
	        ForgeRegistries.ITEMS.register(itemBlock);
	
	        for (int i = 0; i < BlockTFEndOre.Type.values().length; i++) {
	
	            String oreName = BlockTFEndOre.Type.byMetadata(i).getName();
	            ItemStack oreStack = new ItemStack(TF_END_ORE, 1, i);
	
	            OreDictionary.registerOre("oreNether" + StringHelper.titleCase(oreName), oreStack); // No reason to have a specific oreEnd* dictionary
	        }
	
	        // Register Thermal Foundation Fluid Mana effect
	        IBlockState endLead = TF_END_ORE.getDefaultState().withProperty(BlockTFEndOre.VARIANT, BlockTFEndOre.Type.LEAD);
	        IBlockState endGold = VANILLA_END_ORE.getDefaultState().withProperty(BlockVanillaEndOre.VARIANT, BlockVanillaEndOre.Type.GOLD);
	        ((BlockFluidInteractive) TFFluids.blockFluidMana).addInteraction(endLead, endGold);
	
	        IBlockState endSilver  = TF_END_ORE.getDefaultState().withProperty(BlockTFEndOre.VARIANT, BlockTFEndOre.Type.SILVER);
	        IBlockState endMithril = TF_END_ORE.getDefaultState().withProperty(BlockTFEndOre.VARIANT, BlockTFEndOre.Type.MITHRIL);
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
        
    }

    @SideOnly(Side.CLIENT)
    public static void preInitClient() {
    	// Register Custom Models for Items of all the blocks
        Item item;
        ModelResourceLocation model;

        if (NFConfig.HELLFISH) {

            item = Item.getItemFromBlock(HELLFISH);
            model = new ModelResourceLocation(HELLFISH.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(item, 0, model);

        }

        // Old Nether Block
        /*if (NFConfig.OLD_NETHER_ORES) {
	        for (int i = 0; i < BlockNetherOre.Type.values().length; i++) {
	
	            item = Item.getItemFromBlock(ORE);
	            model = new ModelResourceLocation(ORE.getRegistryName(), "type=" + BlockNetherOre.Type.byMetadata(i).getName());
	            ModelLoader.setCustomModelResourceLocation(item, i, model);
	        }
        }*/
        // New Nether Block for Vanilla Ores
        for (int i = 0; i < BlockVanillaOre.Type.values().length; i++) {

            item = Item.getItemFromBlock(VANILLA_ORE);
            model = new ModelResourceLocation(VANILLA_ORE.getRegistryName(), "type=" + BlockVanillaOre.Type.byMetadata(i).getName());
            ModelLoader.setCustomModelResourceLocation(item, i, model);
        }
        // New Nether Block for TF Ores
        for (int i = 0; i < BlockTFOre.Type.values().length; i++) {

            item = Item.getItemFromBlock(TF_ORE);
            model = new ModelResourceLocation(TF_ORE.getRegistryName(), "type=" + BlockTFOre.Type.byMetadata(i).getName());
            ModelLoader.setCustomModelResourceLocation(item, i, model);
        }
        
        if ( NFConfig.END_ORES ) {
        	// Old End Block
            /*if (NFConfig.OLD_END_ORES) {
		        for (int i = 0; i < BlockEndOre.Type.values().length; i++) {
		
		            item = Item.getItemFromBlock(END_ORE);
		            model = new ModelResourceLocation(END_ORE.getRegistryName(), "type=" + BlockEndOre.Type.byMetadata(i).getName());
		            ModelLoader.setCustomModelResourceLocation(item, i, model);
		        }
            }*/
	        // New End Block for Vanilla Ores
	        for (int i = 0; i < BlockVanillaEndOre.Type.values().length; i++) {
	            item = Item.getItemFromBlock(VANILLA_END_ORE);
	            model = new ModelResourceLocation(VANILLA_END_ORE.getRegistryName(), "type=" + BlockVanillaEndOre.Type.byMetadata(i).getName());
	            ModelLoader.setCustomModelResourceLocation(item, i, model);
	        }
	        // New End Block for TF Ores
	        for (int i = 0; i < BlockTFEndOre.Type.values().length; i++) {
	            item = Item.getItemFromBlock(TF_END_ORE);
	            model = new ModelResourceLocation(TF_END_ORE.getRegistryName(), "type=" + BlockTFEndOre.Type.byMetadata(i).getName());
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
    }
}
