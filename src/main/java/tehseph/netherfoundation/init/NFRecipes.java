package tehseph.netherfoundation.init;

import cofh.core.util.helpers.ItemHelper;
import cofh.core.util.helpers.RecipeHelper;
import cofh.thermalfoundation.block.BlockOre;
import cofh.thermalfoundation.init.TFProps;
import cofh.thermalfoundation.item.ItemMaterial;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

public class NFRecipes {

    public static void initCommon() {

        if (TFProps.enablePetrotheumCrafting) addPetrotheumCrafting();
        if (TFProps.enablePyrotheumCrafting)  addPyrotheumCrafting();

        if (TFProps.enablePetrotheumCrafting && TFProps.enablePyrotheumCrafting) addPetroPyroCrafting();

        addSmelting();

    }

    // TODO: Clean this shit up
    private static void addPetrotheumCrafting() {

        int stackSize = NFConfig.PROCESSING_SMELT_TO_ORES ? 3 : 2;

        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.dustGold, stackSize), "oreNetherGold", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.dustIron, stackSize), "oreNetherIron", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.COAL, stackSize, 0), "oreNetherCoal", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, stackSize, EnumDyeColor.BLUE.getDyeDamage()), "oreNetherLapis", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Items.DIAMOND, stackSize), "oreNetherDiamond", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Items.REDSTONE, stackSize), "oreNetherRedstone", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.dustCopper, stackSize), "oreNetherCopper", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.dustTin, stackSize), "oreNetherTin", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.dustSilver, stackSize), "oreNetherSilver", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.dustLead, stackSize), "oreNetherLead", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.dustAluminum, stackSize), "oreNetherAluminum", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.dustNickel, stackSize), "oreNetherNickel", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.dustPlatinum, stackSize), "oreNetherPlatinum", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.dustIridium, stackSize), "oreNetherIridium", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.dustMithril, stackSize), "oreNetherMithril", "dustPetrotheum");

    }

    // TODO: Clean this shit up
    private static void addPyrotheumCrafting() {

        if (NFConfig.PROCESSING_SMELT_TO_ORES) {

            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Blocks.GOLD_ORE, 2), "oreNetherGold", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Blocks.IRON_ORE, 2), "oreNetherIron", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Blocks.COAL_ORE, 2), "oreNetherCoal", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Blocks.LAPIS_ORE, 2), "oreNetherLapis", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Blocks.DIAMOND_ORE, 2), "oreNetherDiamond", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Blocks.REDSTONE_ORE, 2), "oreNetherRedstone", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(BlockOre.oreCopper, 2), "oreNetherCopper", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(BlockOre.oreTin, 2), "oreNetherTin", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(BlockOre.oreSilver, 2), "oreNetherSilver", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(BlockOre.oreLead, 2), "oreNetherLead", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(BlockOre.oreAluminum, 2), "oreNetherAluminum", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(BlockOre.oreNickel, 2), "oreNetherNickel", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(BlockOre.orePlatinum, 2), "oreNetherPlatinum", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(BlockOre.oreIridium, 2), "oreNetherIridium", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(BlockOre.oreMithril, 2), "oreNetherMithril", "dustPyrotheum");

        } else {

            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Items.GOLD_INGOT, 2), "oreNetherGold", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Items.IRON_INGOT, 2), "oreNetherIron", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(new ItemStack(Items.COAL, 2, 0), "oreNetherCoal", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, 2, EnumDyeColor.BLUE.getDyeDamage()), "oreNetherLapis", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Items.DIAMOND, 2), "oreNetherDiamond", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Items.REDSTONE, 2), "oreNetherRedstone", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotCopper, 2), "oreNetherCopper", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotTin, 2), "oreNetherTin", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotSilver, 2), "oreNetherSilver", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotLead, 2), "oreNetherLead", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotAluminum, 2), "oreNetherAluminum", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotNickel, 2), "oreNetherNickel", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotPlatinum, 2), "oreNetherPlatinum", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotIridium, 2), "oreNetherIridium", "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotMithril, 2), "oreNetherMithril", "dustPyrotheum");

        }

    }

    // TODO: Clean this shit up
    private static void addPetroPyroCrafting() {

        int stackSize = NFConfig.PROCESSING_SMELT_TO_ORES ? 3 : 2;

        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Items.GOLD_INGOT, stackSize), "oreNetherGold", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Items.IRON_INGOT, stackSize), "oreNetherIron", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.COAL, stackSize, 0), "oreNetherCoal", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(new ItemStack(Items.DYE, stackSize, EnumDyeColor.BLUE.getDyeDamage()), "oreNetherLapis", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Items.DIAMOND, stackSize), "oreNetherDiamond", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(Items.REDSTONE, stackSize), "oreNetherRedstone", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotCopper, stackSize), "oreNetherCopper", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotTin, stackSize), "oreNetherTin", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotSilver, stackSize), "oreNetherSilver", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotLead, stackSize), "oreNetherLead", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotAluminum, stackSize), "oreNetherAluminum", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotNickel, stackSize), "oreNetherNickel", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotPlatinum, stackSize), "oreNetherPlatinum", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotIridium, stackSize), "oreNetherIridium", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(ItemMaterial.ingotMithril, stackSize), "oreNetherMithril", "dustPetrotheum", "dustPyrotheum");

    }

    // TODO: Clean this shit up
    private static void addSmelting() {

        if (NFConfig.PROCESSING_SMELT_TO_ORES) {

            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 0),  ItemHelper.cloneStack(Blocks.GOLD_ORE, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 1),  ItemHelper.cloneStack(Blocks.IRON_ORE, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 2),  ItemHelper.cloneStack(Blocks.COAL_ORE, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 3),  ItemHelper.cloneStack(Blocks.LAPIS_ORE, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 4),  ItemHelper.cloneStack(Blocks.DIAMOND_ORE, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 5),  ItemHelper.cloneStack(Blocks.REDSTONE_ORE, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 6),  ItemHelper.cloneStack(BlockOre.oreCopper, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 7),  ItemHelper.cloneStack(BlockOre.oreTin, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 8),  ItemHelper.cloneStack(BlockOre.oreSilver, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 9),  ItemHelper.cloneStack(BlockOre.oreLead, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 10), ItemHelper.cloneStack(BlockOre.oreAluminum, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 11), ItemHelper.cloneStack(BlockOre.oreNickel, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 12), ItemHelper.cloneStack(BlockOre.orePlatinum, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 13), ItemHelper.cloneStack(BlockOre.oreIridium, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 14), ItemHelper.cloneStack(BlockOre.oreMithril, 2));

        } else {

            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 0),  ItemHelper.cloneStack(Items.GOLD_INGOT, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 1),  ItemHelper.cloneStack(Items.IRON_INGOT, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 2),  new ItemStack(Items.COAL, 2, 0));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 3),  new ItemStack(Items.DYE, 2, EnumDyeColor.BLUE.getDyeDamage()));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 4),  ItemHelper.cloneStack(Items.DIAMOND, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 5),  ItemHelper.cloneStack(Items.REDSTONE, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 6),  ItemHelper.cloneStack(ItemMaterial.ingotCopper, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 7),  ItemHelper.cloneStack(ItemMaterial.ingotTin, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 8),  ItemHelper.cloneStack(ItemMaterial.ingotSilver, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 9),  ItemHelper.cloneStack(ItemMaterial.ingotLead, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 10), ItemHelper.cloneStack(ItemMaterial.ingotAluminum, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 11), ItemHelper.cloneStack(ItemMaterial.ingotNickel, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 12), ItemHelper.cloneStack(ItemMaterial.ingotPlatinum, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 13), ItemHelper.cloneStack(ItemMaterial.ingotIridium, 2));
            RecipeHelper.addSmelting(new ItemStack(NFBlocks.NETHER_ORE, 1, 14), ItemHelper.cloneStack(ItemMaterial.ingotMithril, 2));

        }

    }

}
