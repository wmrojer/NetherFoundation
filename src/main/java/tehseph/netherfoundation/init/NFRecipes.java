package tehseph.netherfoundation.init;

import cofh.core.util.helpers.ItemHelper;
import cofh.core.util.helpers.RecipeHelper;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SmelterManager;
import cofh.thermalfoundation.init.TFProps;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class NFRecipes {

    private static final ItemStack COAL = new ItemStack(Items.COAL, 1, 0);

    public static void initCommon() {

        if (TFProps.enablePetrotheumCrafting) addPetrotheumCrafting();
        if (TFProps.enablePyrotheumCrafting) addPyrotheumCrafting();

        if (TFProps.enablePetrotheumCrafting && TFProps.enablePyrotheumCrafting) addPetroPyroCrafting();

        if (Loader.isModLoaded("thermalexpansion")) {

            if (NFConfig.PROCESSING_INDUCTION_SMELTER) addInductionSmelterCrafting();
            if (NFConfig.PROCESSING_PULVERIZER) addPulverizerCrafting();

        }

        addSmelting();

    }

    private static void addPetrotheumCrafting() {

        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustGold", 3), "oreNetherGold", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustIron", 3), "oreNetherIron", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(COAL, 3), "oreNetherCoal", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("gemLapis", 3), "oreNetherLapis", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("gemDiamond", 3), "oreNetherDiamond", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustRedstone", 3), "oreNetherRedstone", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustCopper", 3), "oreNetherCopper", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustTin", 3), "oreNetherTin", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustSilver", 3), "oreNetherSilver", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustLead", 3), "oreNetherLead", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustAluminum", 3), "oreNetherAluminum", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustNickel", 3), "oreNetherNickel", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustPlatinum", 3), "oreNetherPlatinum", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustIridium", 3), "oreNetherIridium", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustMithril", 3), "oreNetherMithril", "dustPetrotheum");

    }

    private static void addPyrotheumCrafting() {

        String oreType = NFConfig.PROCESSING_SMELT_TO_ORES ? "ore" : "ingot";

        ItemStack coalStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreCoal", 2) : ItemHelper.cloneStack(COAL, 2);
        ItemStack lapisStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreLapis", 2) : ItemHelper.getOre("gemLapis", 2);
        ItemStack diamondStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreDiamond", 2) : ItemHelper.getOre("gemDiamond", 2);
        ItemStack redstoneStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreRedstone", 2) : ItemHelper.getOre("dustRedstone", 2);

        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Gold", 2), "oreNetherGold", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Iron", 2), "oreNetherIron", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(coalStack, "oreNetherCoal", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(lapisStack, "oreNetherLapis", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(diamondStack, "oreNetherDiamond", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(redstoneStack, "oreNetherRedstone", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Copper", 2), "oreNetherCopper", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Tin", 2), "oreNetherTin", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Silver", 2), "oreNetherSilver", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Lead", 2), "oreNetherLead", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Aluminum", 2), "oreNetherAluminum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Nickel", 2), "oreNetherNickel", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Platinum", 2), "oreNetherPlatinum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Iridium", 2), "oreNetherIridium", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Mithril", 2), "oreNetherMithril", "dustPyrotheum");

    }

    private static void addPetroPyroCrafting() {

        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotGold", 3), "oreNetherGold", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotIron", 3), "oreNetherIron", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotCopper", 3), "oreNetherCopper", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotTin", 3), "oreNetherTin", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotSilver", 3), "oreNetherSilver", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotLead", 3), "oreNetherLead", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotAluminum", 3), "oreNetherAluminum", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotNickel", 3), "oreNetherNickel", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotPlatinum", 3), "oreNetherPlatinum", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotIridium", 3), "oreNetherIridium", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotMithril", 3), "oreNetherMithril", "dustPetrotheum", "dustPyrotheum");

    }

    private static void addInductionSmelterCrafting() {

        addSmelterRecipes(ItemHelper.getOre("oreNetherGold"), ItemHelper.getOre("ingotGold", 3), ItemHelper.getOre("ingotGold", 5));
        addSmelterRecipes(ItemHelper.getOre("oreNetherIron"), ItemHelper.getOre("ingotIron", 3), ItemHelper.getOre("ingotIron", 5));
        addSmelterRecipes(ItemHelper.getOre("oreNetherCopper"), ItemHelper.getOre("ingotCopper", 3), ItemHelper.getOre("ingotCopper", 5));
        addSmelterRecipes(ItemHelper.getOre("oreNetherTin"), ItemHelper.getOre("ingotTin", 3), ItemHelper.getOre("ingotTin", 5));
        addSmelterRecipes(ItemHelper.getOre("oreNetherSilver"), ItemHelper.getOre("ingotSilver", 3), ItemHelper.getOre("ingotSilver", 5));
        addSmelterRecipes(ItemHelper.getOre("oreNetherLead"), ItemHelper.getOre("ingotLead", 3), ItemHelper.getOre("ingotLead", 5));
        addSmelterRecipes(ItemHelper.getOre("oreNetherAluminum"), ItemHelper.getOre("ingotAluminum", 3), ItemHelper.getOre("ingotAluminum", 5));
        addSmelterRecipes(ItemHelper.getOre("oreNetherNickel"), ItemHelper.getOre("ingotNickel", 3), ItemHelper.getOre("ingotNickel", 5));
        addSmelterRecipes(ItemHelper.getOre("oreNetherPlatinum"), ItemHelper.getOre("ingotPlatinum", 3), ItemHelper.getOre("ingotPlatinum", 5));
        addSmelterRecipes(ItemHelper.getOre("oreNetherIridium"), ItemHelper.getOre("ingotIridium", 3), ItemHelper.getOre("ingotIridium", 5));
        addSmelterRecipes(ItemHelper.getOre("oreNetherMithril"), ItemHelper.getOre("ingotMithril", 3), ItemHelper.getOre("ingotMithril", 5));

        SmelterManager.addRecipe(4000, ItemHelper.getOre("sand"), ItemHelper.getOre("oreNetherLapis"), ItemHelper.getOre("gemLapis", 15), ItemHelper.getOre("crystalSlagRich"), 50);
        SmelterManager.addRecipe(4000, ItemHelper.getOre("sand"), ItemHelper.getOre("oreNetherRedstone"), ItemHelper.getOre("dustRedstone", 12), ItemHelper.getOre("crystalSlagRich"), 50);

    }

    private static void addSmelterRecipes(ItemStack input, ItemStack outputSand, ItemStack outputSlag) {

        SmelterManager.addRecipe(4000, ItemHelper.getOre("sand"), input, outputSand, ItemHelper.getOre("crystalSlagRich"), 10);
        SmelterManager.addRecipe(4000, ItemHelper.getOre("crystalSlagRich"), input, outputSlag, ItemHelper.getOre("crystalSlag"), 75);

    }

    private static void addPulverizerCrafting() {

        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherGold"), ItemHelper.getOre("dustGold", 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherIron"), ItemHelper.getOre("dustIron", 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherCoal"), ItemHelper.cloneStack(COAL, 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherLapis"), ItemHelper.getOre("gemLapis", 12), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherDiamond"), ItemHelper.getOre("gemDiamond", 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherRedstone"), ItemHelper.getOre("dustRedstone", 9), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherCopper"), ItemHelper.getOre("dustCopper", 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherTin"), ItemHelper.getOre("dustTin", 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherSilver"), ItemHelper.getOre("dustSilver", 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherLead"), ItemHelper.getOre("dustLead", 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherAluminum"), ItemHelper.getOre("dustAluminum", 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherNickel"), ItemHelper.getOre("dustNickel", 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherPlatinum"), ItemHelper.getOre("dustPlatinum", 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherIridium"), ItemHelper.getOre("dustIridium", 3), ItemHelper.getOre("dustSulfur"), 75);
        PulverizerManager.addRecipe(4000, ItemHelper.getOre("oreNetherMithril"), ItemHelper.getOre("dustMithril", 3), ItemHelper.getOre("dustSulfur"), 75);

    }

    private static void addSmelting() {

        String oreType = NFConfig.PROCESSING_SMELT_TO_ORES ? "ore" : "ingot";

        ItemStack coalStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreCoal", 2) : ItemHelper.cloneStack(COAL, 2);
        ItemStack lapisStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreLapis", 2) : ItemHelper.getOre("gemLapis", 2);
        ItemStack diamondStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreDiamond", 2) : ItemHelper.getOre("gemDiamond", 2);
        ItemStack redstoneStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreRedstone", 2) : ItemHelper.getOre("dustRedstone", 2);

        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherGold"), ItemHelper.getOre(oreType + "Gold", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherIron"), ItemHelper.getOre(oreType + "Iron", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherCoal"), coalStack);
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherLapis"), lapisStack);
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherDiamond"), diamondStack);
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherRedstone"), redstoneStack);
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherCopper"), ItemHelper.getOre(oreType + "Copper", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherTin"), ItemHelper.getOre(oreType + "Tin", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherSilver"), ItemHelper.getOre(oreType + "Silver", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherLead"), ItemHelper.getOre(oreType + "Lead", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherAluminum"), ItemHelper.getOre(oreType + "Aluminum", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherNickel"), ItemHelper.getOre(oreType + "Nickel", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherPlatinum"), ItemHelper.getOre(oreType + "Platinum", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherIridium"), ItemHelper.getOre(oreType + "Iridium", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherMithril"), ItemHelper.getOre(oreType + "Mithril", 2));

    }

}
