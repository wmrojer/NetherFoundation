package tehseph.netherfoundation.init;

import cofh.api.util.ThermalExpansionHelper;
import cofh.core.util.helpers.ItemHelper;
import cofh.core.util.helpers.RecipeHelper;
import cofh.thermalfoundation.init.TFProps;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class NFRecipes {

    private static final ItemStack COAL = new ItemStack(Items.COAL, 1, 0);

    public static void initCommon() {

        if (NFConfig.PROCESSING_PETROTHEUM && TFProps.enablePetrotheumCrafting) addPetrotheumCrafting();
        if (NFConfig.PROCESSING_PYROTHEUM  && TFProps.enablePyrotheumCrafting)  addPyrotheumCrafting();

        if (NFConfig.PROCESSING_PETROTHEUM && NFConfig.PROCESSING_PYROTHEUM) {
            if (TFProps.enablePetrotheumCrafting && TFProps.enablePyrotheumCrafting) addPetroPyroCrafting();
        }

        if (Loader.isModLoaded("thermalexpansion")) {

            if (NFConfig.PROCESSING_INDUCTION_SMELTER) addInductionSmelterCrafting();
            if (NFConfig.PROCESSING_PULVERIZER)        addPulverizerCrafting();

        }

        if (NFConfig.PROCESSING_FURNACE) addSmelting();

    }

    private static void addPetrotheumCrafting() {

        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustGold", 3),     "oreNetherGold",     "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustIron", 3),     "oreNetherIron",     "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(COAL, 3),       "oreNetherCoal",     "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("gemLapis", 3),     "oreNetherLapis",    "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("gemDiamond", 3),   "oreNetherDiamond",  "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustRedstone", 3), "oreNetherRedstone", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustCopper", 3),   "oreNetherCopper",   "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustTin", 3),      "oreNetherTin",      "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustSilver", 3),   "oreNetherSilver",   "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustLead", 3),     "oreNetherLead",     "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustAluminum", 3), "oreNetherAluminum", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustNickel", 3),   "oreNetherNickel",   "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustPlatinum", 3), "oreNetherPlatinum", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustIridium", 3),  "oreNetherIridium",  "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustMithril", 3),  "oreNetherMithril",  "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("gemEmerald", 3),   "oreNetherEmerald",  "dustPetrotheum");

    }

    private static void addPyrotheumCrafting() {

        String oreType = NFConfig.PROCESSING_SMELT_TO_ORES ? "ore" : "ingot";

        ItemStack coalStack     = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreCoal", 2)     : ItemHelper.cloneStack(COAL, 2);
        ItemStack lapisStack    = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreLapis", 2)    : ItemHelper.getOre("gemLapis", 2);
        ItemStack diamondStack  = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreDiamond", 2)  : ItemHelper.getOre("gemDiamond", 2);
        ItemStack redstoneStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreRedstone", 2) : ItemHelper.getOre("dustRedstone", 2);
        ItemStack emeraldStack  = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreEmerald", 2)  : ItemHelper.getOre("gemEmerald", 2);

        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Gold", 2), "oreNetherGold", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Iron", 2), "oreNetherIron", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(coalStack,     "oreNetherCoal",     "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(lapisStack,    "oreNetherLapis",    "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(diamondStack,  "oreNetherDiamond",  "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(redstoneStack, "oreNetherRedstone", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Copper", 2),   "oreNetherCopper",   "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Tin", 2),      "oreNetherTin",      "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Silver", 2),   "oreNetherSilver",   "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Lead", 2),     "oreNetherLead",     "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Aluminum", 2), "oreNetherAluminum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Nickel", 2),   "oreNetherNickel",   "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Platinum", 2), "oreNetherPlatinum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Iridium", 2),  "oreNetherIridium",  "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre(oreType + "Mithril", 2),  "oreNetherMithril",  "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(emeraldStack, "oreNetherEmerald", "dustPyrotheum");

    }

    private static void addPetroPyroCrafting() {

        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotGold", 3),     "oreNetherGold",     "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotIron", 3),     "oreNetherIron",     "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotCopper", 3),   "oreNetherCopper",   "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotTin", 3),      "oreNetherTin",      "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotSilver", 3),   "oreNetherSilver",   "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotLead", 3),     "oreNetherLead",     "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotAluminum", 3), "oreNetherAluminum", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotNickel", 3),   "oreNetherNickel",   "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotPlatinum", 3), "oreNetherPlatinum", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotIridium", 3),  "oreNetherIridium",  "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotMithril", 3),  "oreNetherMithril",  "dustPetrotheum", "dustPyrotheum");

    }

    private static void addInductionSmelterCrafting() {

        int countSand = NFConfig.PROCESSING_SMELT_TO_ORES ? 3 : 4;
        int countSlag = NFConfig.PROCESSING_SMELT_TO_ORES ? 5 : 6;

        addSmelterRecipes(ItemHelper.getOre("oreNetherGold"),     ItemHelper.getOre("ingotGold", countSand),     ItemHelper.getOre("ingotGold", countSlag));
        addSmelterRecipes(ItemHelper.getOre("oreNetherIron"),     ItemHelper.getOre("ingotIron", countSand),     ItemHelper.getOre("ingotIron", countSlag));
        addSmelterRecipes(ItemHelper.getOre("oreNetherCopper"),   ItemHelper.getOre("ingotCopper", countSand),   ItemHelper.getOre("ingotCopper", countSlag));
        addSmelterRecipes(ItemHelper.getOre("oreNetherTin"),      ItemHelper.getOre("ingotTin", countSand),      ItemHelper.getOre("ingotTin", countSlag));
        addSmelterRecipes(ItemHelper.getOre("oreNetherSilver"),   ItemHelper.getOre("ingotSilver", countSand),   ItemHelper.getOre("ingotSilver", countSlag));
        addSmelterRecipes(ItemHelper.getOre("oreNetherLead"),     ItemHelper.getOre("ingotLead", countSand),     ItemHelper.getOre("ingotLead", countSlag));
        addSmelterRecipes(ItemHelper.getOre("oreNetherAluminum"), ItemHelper.getOre("ingotAluminum", countSand), ItemHelper.getOre("ingotAluminum", countSlag));
        addSmelterRecipes(ItemHelper.getOre("oreNetherNickel"),   ItemHelper.getOre("ingotNickel", countSand),   ItemHelper.getOre("ingotNickel", countSlag));
        addSmelterRecipes(ItemHelper.getOre("oreNetherPlatinum"), ItemHelper.getOre("ingotPlatinum", countSand), ItemHelper.getOre("ingotPlatinum", countSlag));
        addSmelterRecipes(ItemHelper.getOre("oreNetherIridium"),  ItemHelper.getOre("ingotIridium", countSand),  ItemHelper.getOre("ingotIridium", countSlag));
        addSmelterRecipes(ItemHelper.getOre("oreNetherMithril"),  ItemHelper.getOre("ingotMithril", countSand),  ItemHelper.getOre("ingotMithril", countSlag));

        ItemStack outputLapis = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("gemLapis", 15) : ItemHelper.getOre("gemLapis", 20);
        ItemStack outputRedstone = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("dustRedstone", 12) : ItemHelper.getOre("dustRedstone", 16);

        ThermalExpansionHelper.addSmelterRecipe(4000, ItemHelper.getOre("sand"), ItemHelper.getOre("oreNetherLapis"), outputLapis, ItemHelper.getOre("crystalSlagRich"), 50);
        ThermalExpansionHelper.addSmelterRecipe(4000, ItemHelper.getOre("sand"), ItemHelper.getOre("oreNetherRedstone"), outputRedstone, ItemHelper.getOre("crystalSlagRich"), 50);

    }

    private static void addSmelterRecipes(ItemStack input, ItemStack outputSand, ItemStack outputSlag) {

        ThermalExpansionHelper.addSmelterRecipe(4000, ItemHelper.getOre("sand"), input, outputSand, ItemHelper.getOre("crystalSlagRich"), 10);
        ThermalExpansionHelper.addSmelterRecipe(4000, ItemHelper.getOre("crystalSlagRich"), input, outputSlag, ItemHelper.getOre("crystalSlag"), 75);

    }

    private static void addPulverizerCrafting() {

        int countCommon   = NFConfig.PROCESSING_SMELT_TO_ORES ? 3 : 4;
        int countLapis    = NFConfig.PROCESSING_SMELT_TO_ORES ? 12 : 16;
        int countRedstone = NFConfig.PROCESSING_SMELT_TO_ORES ? 9 : 12;

        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherGold"),     ItemHelper.getOre("dustGold", countCommon),       ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherIron"),     ItemHelper.getOre("dustIron", countCommon),       ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherCoal"),     ItemHelper.cloneStack(COAL, countCommon),         ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherLapis"),    ItemHelper.getOre("gemLapis", countLapis),        ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherDiamond"),  ItemHelper.getOre("gemDiamond", countCommon),     ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherRedstone"), ItemHelper.getOre("dustRedstone", countRedstone), ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherCopper"),   ItemHelper.getOre("dustCopper", countCommon),     ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherTin"),      ItemHelper.getOre("dustTin", countCommon),        ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherSilver"),   ItemHelper.getOre("dustSilver", countCommon),     ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherLead"),     ItemHelper.getOre("dustLead", countCommon),       ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherAluminum"), ItemHelper.getOre("dustAluminum", countCommon),   ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherNickel"),   ItemHelper.getOre("dustNickel", countCommon),     ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherPlatinum"), ItemHelper.getOre("dustPlatinum", countCommon),   ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherIridium"),  ItemHelper.getOre("dustIridium", countCommon),    ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherMithril"),  ItemHelper.getOre("dustMithril", countCommon),    ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherEmerald"),  ItemHelper.getOre("gemEmerald", countCommon),     ItemHelper.getOre("dustSulfur"), 75);

    }

    private static void addSmelting() {

        String oreType = NFConfig.PROCESSING_SMELT_TO_ORES ? "ore" : "ingot";

        ItemStack coalStack     = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreCoal", 2)     : ItemHelper.cloneStack(COAL, 2);
        ItemStack lapisStack    = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreLapis", 2)    : ItemHelper.getOre("gemLapis", 2);
        ItemStack diamondStack  = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreDiamond", 2)  : ItemHelper.getOre("gemDiamond", 2);
        ItemStack redstoneStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreRedstone", 2) : ItemHelper.getOre("dustRedstone", 2);
        ItemStack emeraldStack  = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreEmerald", 2)  : ItemHelper.getOre("gemEmerald", 2);

        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherGold"),     ItemHelper.getOre(oreType + "Gold", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherIron"),     ItemHelper.getOre(oreType + "Iron", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherCoal"),     coalStack);
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherLapis"),    lapisStack);
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherDiamond"),  diamondStack);
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherRedstone"), redstoneStack);
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherCopper"),   ItemHelper.getOre(oreType + "Copper", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherTin"),      ItemHelper.getOre(oreType + "Tin", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherSilver"),   ItemHelper.getOre(oreType + "Silver", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherLead"),     ItemHelper.getOre(oreType + "Lead", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherAluminum"), ItemHelper.getOre(oreType + "Aluminum", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherNickel"),   ItemHelper.getOre(oreType + "Nickel", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherPlatinum"), ItemHelper.getOre(oreType + "Platinum", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherIridium"),  ItemHelper.getOre(oreType + "Iridium", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherMithril"),  ItemHelper.getOre(oreType + "Mithril", 2));
        RecipeHelper.addSmelting(ItemHelper.getOre("oreNetherEmerald"),  emeraldStack);

    }

}
