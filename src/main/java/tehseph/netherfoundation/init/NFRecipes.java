package tehseph.netherfoundation.init;

import cofh.core.util.helpers.ItemHelper;
import cofh.core.util.helpers.RecipeHelper;
import cofh.thermalfoundation.init.TFProps;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class NFRecipes {

    private static final ItemStack COAL = new ItemStack(Items.COAL, 1, 0);

    public static void initCommon() {

        if (TFProps.enablePetrotheumCrafting) addPetrotheumCrafting();
        if (TFProps.enablePyrotheumCrafting)  addPyrotheumCrafting();

        if (TFProps.enablePetrotheumCrafting && TFProps.enablePyrotheumCrafting) addPetroPyroCrafting();

        addSmelting();

    }

    private static void addPetrotheumCrafting() {

        int stackSize = NFConfig.PROCESSING_SMELT_TO_ORES ? 3 : 2;

        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustGold", stackSize), "oreNetherGold", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustIron", stackSize), "oreNetherIron", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.cloneStack(COAL, stackSize), "oreNetherCoal", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("gemLapis", stackSize), "oreNetherLapis", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("gemDiamond", stackSize), "oreNetherDiamond", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustRedstone", stackSize), "oreNetherRedstone", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustCopper", stackSize), "oreNetherCopper", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustTin", stackSize), "oreNetherTin", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustSilver", stackSize), "oreNetherSilver", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustLead", stackSize), "oreNetherLead", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustAluminum", stackSize), "oreNetherAluminum", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustNickel", stackSize), "oreNetherNickel", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustPlatinum", stackSize), "oreNetherPlatinum", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustIridium", stackSize), "oreNetherIridium", "dustPetrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustMithril", stackSize), "oreNetherMithril", "dustPetrotheum");

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

        int stackSize = NFConfig.PROCESSING_SMELT_TO_ORES ? 3 : 2;

        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotGold", stackSize), "oreNetherGold", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotIron", stackSize), "oreNetherIron", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotCopper", stackSize), "oreNetherCopper", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotTin", stackSize), "oreNetherTin", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotSilver", stackSize), "oreNetherSilver", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotLead", stackSize), "oreNetherLead", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotAluminum", stackSize), "oreNetherAluminum", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotNickel", stackSize), "oreNetherNickel", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotPlatinum", stackSize), "oreNetherPlatinum", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotIridium", stackSize), "oreNetherIridium", "dustPetrotheum", "dustPyrotheum");
        RecipeHelper.addShapelessRecipe(ItemHelper.getOre("ingotMithril", stackSize), "oreNetherMithril", "dustPetrotheum", "dustPyrotheum");

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
