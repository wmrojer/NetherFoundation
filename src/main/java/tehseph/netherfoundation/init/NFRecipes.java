package tehseph.netherfoundation.init;

import java.util.Optional;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import cofh.api.util.ThermalExpansionHelper;
import cofh.core.util.helpers.ItemHelper;
import cofh.core.util.helpers.RecipeHelper;
import cofh.thermalfoundation.init.TFProps;

import appeng.api.AEApi;
import appeng.api.definitions.IMaterials;
import appeng.api.features.IGrinderRecipeBuilder;
import appeng.api.features.IGrinderRegistry;

import tehseph.netherfoundation.common.block.BlockVanillaOre;
import tehseph.netherfoundation.common.block.BlockTFOre;
import tehseph.netherfoundation.common.block.BlockVanillaEndOre;
import tehseph.netherfoundation.common.block.BlockTFEndOre;
import tehseph.netherfoundation.common.block.BlockAE2Ore;

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

            if (NFConfig.PROCESSING_MAGMA_CRUCIBLE)    addMagmaCrucibleCrafting();
            if (NFConfig.PROCESSING_ENERGETIC_INFUSER) addChargingCrafting();
        }
        if (NFConfig.PROCESSING_FURNACE) addSmelting();
        
        if (NFConfig.PROCESSING_AE2_GRINDER) addAE2GrinderCrafting();
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

        if ( NFConfig.AE2_ORES ) {
            RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustCertusQuartz", 3),  "oreNetherCertusQuartz",  "dustPetrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.getOre("dustCertusQuartz", 3),  "oreNetherChargedCertusQuartz",  "dustPetrotheum");
        }
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

        if ( NFConfig.AE2_ORES && NFConfig.PROCESSING_SMELT_TO_ORES) {
            RecipeHelper.addShapelessRecipe(ItemHelper.getOre("oreCertusQuartz", 2),  "oreNetherCertusQuartz",  "dustPyrotheum");
            RecipeHelper.addShapelessRecipe(ItemHelper.getOre("oreChargedCertusQuartz", 2),  "oreNetherChargedCertusQuartz",  "dustPyrotheum");
        }

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

        addSmelterRecipes(ItemHelper.getOre("oreNetherGold"),     ItemHelper.getOre("ingotGold", countSand),     ItemHelper.getOre("ingotGold", countSlag), ItemHelper.getOre("crystalSlagRich"), 20, 75);
        addSmelterRecipes(ItemHelper.getOre("oreNetherIron"),     ItemHelper.getOre("ingotIron", countSand),     ItemHelper.getOre("ingotIron", countSlag), ItemHelper.getOre("ingotNickel"));
        addSmelterRecipes(ItemHelper.getOre("oreNetherCopper"),   ItemHelper.getOre("ingotCopper", countSand),   ItemHelper.getOre("ingotCopper", countSlag), ItemHelper.getOre("ingotGold"));
        addSmelterRecipes(ItemHelper.getOre("oreNetherTin"),      ItemHelper.getOre("ingotTin", countSand),      ItemHelper.getOre("ingotTin", countSlag), ItemHelper.getOre("ingotIron"));
        addSmelterRecipes(ItemHelper.getOre("oreNetherSilver"),   ItemHelper.getOre("ingotSilver", countSand),   ItemHelper.getOre("ingotSilver", countSlag), ItemHelper.getOre("ingotLead"));
        addSmelterRecipes(ItemHelper.getOre("oreNetherLead"),     ItemHelper.getOre("ingotLead", countSand),     ItemHelper.getOre("ingotLead", countSlag), ItemHelper.getOre("ingotSilver"));
        addSmelterRecipes(ItemHelper.getOre("oreNetherAluminum"), ItemHelper.getOre("ingotAluminum", countSand), ItemHelper.getOre("ingotAluminum", countSlag), ItemHelper.getOre("ingotIron"));
        addSmelterRecipes(ItemHelper.getOre("oreNetherNickel"),   ItemHelper.getOre("ingotNickel", countSand),   ItemHelper.getOre("ingotNickel", countSlag), ItemHelper.getOre("ingotPlatinum"), 15, 75);
        addSmelterRecipes(ItemHelper.getOre("oreNetherPlatinum"), ItemHelper.getOre("ingotPlatinum", countSand), ItemHelper.getOre("ingotPlatinum", countSlag), ItemHelper.getOre("ingotIridium"));
        addSmelterRecipes(ItemHelper.getOre("oreNetherIridium"),  ItemHelper.getOre("ingotIridium", countSand),  ItemHelper.getOre("ingotIridium", countSlag), ItemHelper.getOre("ingotPlatinum"));
        addSmelterRecipes(ItemHelper.getOre("oreNetherMithril"),  ItemHelper.getOre("ingotMithril", countSand),  ItemHelper.getOre("ingotMithril", countSlag), ItemHelper.getOre("ingotGold"));

        ItemStack outputLapis = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("gemLapis", 12) : ItemHelper.getOre("gemLapis", 16);
        ItemStack outputRedstone = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("dustRedstone", 12) : ItemHelper.getOre("dustRedstone", 16);

        ThermalExpansionHelper.addSmelterRecipe(4000, ItemHelper.getOre("sand"), ItemHelper.getOre("oreNetherLapis"), outputLapis, ItemHelper.getOre("crystalSlagRich"), 50);
        ThermalExpansionHelper.addSmelterRecipe(4000, ItemHelper.getOre("sand"), ItemHelper.getOre("oreNetherRedstone"), outputRedstone, ItemHelper.getOre("crystalSlagRich"), 50);

        if ( NFConfig.AE2_ORES ) {
            IMaterials materialsApi = AEApi.instance().definitions().materials();
            Optional<ItemStack> certusQuartzCrystal = materialsApi.certusQuartzCrystal().maybeStack(countSand);
            if ( certusQuartzCrystal.isPresent() ) {
                ThermalExpansionHelper.addSmelterRecipe(4000, ItemHelper.getOre("sand"), ItemHelper.getOre("oreNetherCertusQuartz"), certusQuartzCrystal.get(), ItemHelper.getOre("crystalSlagRich"), 50);
            }
            Optional<ItemStack> certusQuartzCrystalCharged = materialsApi.certusQuartzCrystalCharged().maybeStack(countSand);
            if ( certusQuartzCrystalCharged.isPresent() ) {
                ThermalExpansionHelper.addSmelterRecipe(4000, ItemHelper.getOre("sand"), ItemHelper.getOre("oreNetherChargedCertusQuartz"), certusQuartzCrystalCharged.get(), ItemHelper.getOre("crystalSlagRich"), 75);
            }
        }
    }

    private static void addSmelterRecipes(ItemStack input, ItemStack outputSand, ItemStack outputSlag, ItemStack outputCinnabar) {
    	addSmelterRecipes(input, outputSand, outputSlag, outputCinnabar, 10, 75);
    }
    
    private static void addSmelterRecipes(ItemStack input, ItemStack outputSand, ItemStack outputSlag, ItemStack outputCinnabar, int chanceSand, int chanceSlag) {

        ThermalExpansionHelper.addSmelterRecipe(4000, ItemHelper.getOre("sand"), input, outputSand, ItemHelper.getOre("crystalSlagRich"), chanceSand);
        ThermalExpansionHelper.addSmelterRecipe(4000, ItemHelper.getOre("crystalSlagRich"), input, outputSlag, ItemHelper.getOre("crystalSlag"), chanceSlag);
        ThermalExpansionHelper.addSmelterRecipe(4000, ItemHelper.getOre("crystalCinnabar"), input, outputSlag, outputCinnabar, 100);

    }

    private static void addPulverizerCrafting() {

        int countCommon   = NFConfig.PROCESSING_SMELT_TO_ORES ? 3 : 4;
        int countCoal   = NFConfig.PROCESSING_SMELT_TO_ORES ? 4 : 6;
        int countLapis    = NFConfig.PROCESSING_SMELT_TO_ORES ? 15 : 20;
        int countRedstone = NFConfig.PROCESSING_SMELT_TO_ORES ? 9 : 12;

        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherGold"),     ItemHelper.getOre("dustGold", countCommon),       ItemHelper.getOre("crystalCinnabar"), 15);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherIron"),     ItemHelper.getOre("dustIron", countCommon),       ItemHelper.getOre("dustNickel"), 15);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherCoal"),     ItemHelper.cloneStack(COAL, countCoal),           ItemHelper.getOre("dustCoal"), 35);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherLapis"),    ItemHelper.getOre("gemLapis", countLapis),        ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherDiamond"),  ItemHelper.getOre("gemDiamond", countCommon),     ItemHelper.getOre("dustSulfur"), 75);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherRedstone"), ItemHelper.getOre("dustRedstone", countRedstone), ItemHelper.getOre("crystalCinnabar"), 35);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherCopper"),   ItemHelper.getOre("dustCopper", countCommon),     ItemHelper.getOre("dustGold"), 15);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherTin"),      ItemHelper.getOre("dustTin", countCommon),        ItemHelper.getOre("dustIron"), 15);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherSilver"),   ItemHelper.getOre("dustSilver", countCommon),     ItemHelper.getOre("dustLead"), 15);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherLead"),     ItemHelper.getOre("dustLead", countCommon),       ItemHelper.getOre("dustSilver"), 15);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherAluminum"), ItemHelper.getOre("dustAluminum", countCommon),   ItemHelper.getOre("dustIron"), 15);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherNickel"),   ItemHelper.getOre("dustNickel", countCommon),     ItemHelper.getOre("dustPlatinum"), 15);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherPlatinum"), ItemHelper.getOre("dustPlatinum", countCommon),   ItemHelper.getOre("dustIridium"), 15);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherIridium"),  ItemHelper.getOre("dustIridium", countCommon),    ItemHelper.getOre("dustPlatinum"), 15);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherMithril"),  ItemHelper.getOre("dustMithril", countCommon),    ItemHelper.getOre("dustGold"), 15);
        ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherEmerald"),  ItemHelper.getOre("gemEmerald", countCommon),     ItemHelper.getOre("dustSulfur"), 75);

        if ( NFConfig.AE2_ORES ) {
            IMaterials materialsApi = AEApi.instance().definitions().materials();
            Optional<ItemStack> certusQuartzCrystal = materialsApi.certusQuartzCrystal().maybeStack(countCommon);
            if ( certusQuartzCrystal.isPresent() ) {
            	ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherCertusQuartz"), certusQuartzCrystal.get(), ItemHelper.getOre("dustCertusQuartz"), 15);
            }
            Optional<ItemStack> certusQuartzCrystalCharged = materialsApi.certusQuartzCrystalCharged().maybeStack(countCommon);
            if ( certusQuartzCrystalCharged.isPresent() ) {
            	ThermalExpansionHelper.addPulverizerRecipe(4000, ItemHelper.getOre("oreNetherChargedCertusQuartz"),certusQuartzCrystalCharged.get(), ItemHelper.getOre("dustCertusQuartz"), 15);
            }
        }
    }

    private static void addSmelting() {

        String oreType = NFConfig.PROCESSING_SMELT_TO_ORES ? "ore" : "ingot";

        ItemStack coalStack     = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreCoal", 2)     : ItemHelper.cloneStack(COAL, 2);
        ItemStack lapisStack    = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreLapis", 2)    : ItemHelper.getOre("gemLapis", 2);
        ItemStack diamondStack  = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreDiamond", 2)  : ItemHelper.getOre("gemDiamond", 2);
        ItemStack redstoneStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreRedstone", 2) : ItemHelper.getOre("dustRedstone", 2);
        ItemStack emeraldStack  = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreEmerald", 2)  : ItemHelper.getOre("gemEmerald", 2);

        addSmeltingOreDict("oreNetherGold",     ItemHelper.getOre(oreType + "Gold", 2));
        addSmeltingOreDict("oreNetherIron",     ItemHelper.getOre(oreType + "Iron", 2));
        addSmeltingOreDict("oreNetherCoal",     coalStack);
        addSmeltingOreDict("oreNetherLapis",    lapisStack);
        addSmeltingOreDict("oreNetherDiamond",  diamondStack);
        addSmeltingOreDict("oreNetherRedstone", redstoneStack);
        addSmeltingOreDict("oreNetherEmerald",  emeraldStack);

        GameRegistry.addSmelting( new ItemStack(NFBlocks.VANILLA_END_ORE, 1, 8), ItemHelper.getOre("gemQuartz", 1), 0.2F); // End Quartz. Can't use OreDict since it returns the normal quartz
        
        addSmeltingOreDict("oreNetherCopper",   ItemHelper.getOre(oreType + "Copper", 2));
        addSmeltingOreDict("oreNetherTin",      ItemHelper.getOre(oreType + "Tin", 2));
        addSmeltingOreDict("oreNetherSilver",   ItemHelper.getOre(oreType + "Silver", 2));
        addSmeltingOreDict("oreNetherLead",     ItemHelper.getOre(oreType + "Lead", 2));
        addSmeltingOreDict("oreNetherAluminum", ItemHelper.getOre(oreType + "Aluminum", 2));
        addSmeltingOreDict("oreNetherNickel",   ItemHelper.getOre(oreType + "Nickel", 2));
        addSmeltingOreDict("oreNetherPlatinum", ItemHelper.getOre(oreType + "Platinum", 2));
        addSmeltingOreDict("oreNetherIridium",  ItemHelper.getOre(oreType + "Iridium", 2));
        addSmeltingOreDict("oreNetherMithril",  ItemHelper.getOre(oreType + "Mithril", 2));
        
        if ( NFConfig.AE2_ORES ) {
            ItemStack certusStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreCertusQuartz", 2) : ItemHelper.getOre("crystalCertusQuartz", 2);
        	
            addSmeltingOreDict("oreNetherCertusQuartz",  certusStack);
            
            certusStack = NFConfig.PROCESSING_SMELT_TO_ORES ? ItemHelper.getOre("oreChargedCertusQuartz", 2) : ItemHelper.getOre("crystalCertusQuartz", 2);
            addSmeltingOreDict("oreNetherChargedCertusQuartz",  certusStack);
        }

    }
    
    // Add smelting recipe for all ores in an oreDictionary. (Can't seem to find a smelting recipe that accepts oreDictionary)
    private static void addSmeltingOreDict(String oreName, ItemStack output ) {
    	if ( ItemHelper.oreNameExists(oreName) ) {
    		
    		NonNullList<ItemStack> oreList = OreDictionary.getOres(oreName, false);
    		for ( ItemStack ore : oreList ) {
    			GameRegistry.addSmelting(ore, output, 0);
    		}
    	}
    }

    private static void addMagmaCrucibleCrafting() {
    	int countCommon   = 576;
    	int energy = 16000;
    	
    	if ( FluidRegistry.isFluidRegistered("gold") )
    		ThermalExpansionHelper.addCrucibleRecipe(energy, ItemHelper.getOre("oreNetherGold"), FluidRegistry.getFluidStack("gold", countCommon)); 
    	if ( FluidRegistry.isFluidRegistered("iron") )
    		ThermalExpansionHelper.addCrucibleRecipe(energy, ItemHelper.getOre("oreNetherIron"), FluidRegistry.getFluidStack("iron", countCommon)); 
    	if ( FluidRegistry.isFluidRegistered("copper") )
    		ThermalExpansionHelper.addCrucibleRecipe(energy, ItemHelper.getOre("oreNetherCopper"), FluidRegistry.getFluidStack("copper", countCommon)); 
    	if ( FluidRegistry.isFluidRegistered("tin") )
    		ThermalExpansionHelper.addCrucibleRecipe(energy, ItemHelper.getOre("oreNetherTin"), FluidRegistry.getFluidStack("tin", countCommon)); 
    	if ( FluidRegistry.isFluidRegistered("silver") )
    		ThermalExpansionHelper.addCrucibleRecipe(energy, ItemHelper.getOre("oreNetherSilver"), FluidRegistry.getFluidStack("silver", countCommon)); 
    	if ( FluidRegistry.isFluidRegistered("lead") )
    		ThermalExpansionHelper.addCrucibleRecipe(energy, ItemHelper.getOre("oreNetherLead"), FluidRegistry.getFluidStack("lead", countCommon)); 
    	if ( FluidRegistry.isFluidRegistered("aluminum") )
    		ThermalExpansionHelper.addCrucibleRecipe(energy, ItemHelper.getOre("oreNetherAluminum"), FluidRegistry.getFluidStack("aluminum", countCommon)); 
    	if ( FluidRegistry.isFluidRegistered("nickel") )
    		ThermalExpansionHelper.addCrucibleRecipe(energy, ItemHelper.getOre("oreNetherNickel"), FluidRegistry.getFluidStack("nickel", countCommon)); 
    	if ( FluidRegistry.isFluidRegistered("platinum") )
    		ThermalExpansionHelper.addCrucibleRecipe(energy, ItemHelper.getOre("oreNetherPlatinum"), FluidRegistry.getFluidStack("platinum", countCommon)); 
    	if ( FluidRegistry.isFluidRegistered("iridium") )
    		ThermalExpansionHelper.addCrucibleRecipe(energy, ItemHelper.getOre("oreNetherIridium"), FluidRegistry.getFluidStack("iridium", countCommon)); 
    }
    
    private static void addChargingCrafting() {
    	int energy = 16000; 
    			
        if ( NFConfig.AE2_ORES ) {
        	ItemStack oreInput = new ItemStack(NFBlocks.AE2_ORE, 1, BlockAE2Ore.Type.CERTUS.getMetadata());
        	ItemStack oreOutput = new ItemStack(NFBlocks.AE2_ORE, 1, BlockAE2Ore.Type.CHARGED_CERTUS.getMetadata());
	    	ThermalExpansionHelper.addChargerRecipe(energy, oreInput, oreOutput);

	    	oreInput = new ItemStack(NFBlocks.AE2_ORE, 1, BlockAE2Ore.Type.END_CERTUS.getMetadata());
	    	oreOutput = new ItemStack(NFBlocks.AE2_ORE, 1, BlockAE2Ore.Type.END_CHARGED_CERTUS.getMetadata());
	    	ThermalExpansionHelper.addChargerRecipe(energy, oreInput, oreOutput);
        }
    }
    
    private static void addAE2GrinderCrafting() {
    	if (Loader.isModLoaded("appliedenergistics2")) {
    		IGrinderRegistry grinder = AEApi.instance().registries().grinder();
    		IGrinderRecipeBuilder builder = grinder.builder();
    		
    		ItemStack output1 = ItemHelper.getOre("dustGold", 1);
    		ItemStack output2 = ItemHelper.getOre("dustGold", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.VANILLA_ORE, 1, BlockVanillaOre.Type.GOLD.getMetadata()) )
					.withOutput( output2 )
					.withFirstOptional( output1, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.VANILLA_END_ORE, 1, BlockVanillaEndOre.Type.GOLD.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		output1 = ItemHelper.getOre("dustIron", 1);
    		output2 = ItemHelper.getOre("dustIron", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.VANILLA_ORE, 1, BlockVanillaOre.Type.IRON.getMetadata()) )
					.withOutput( output2 )
					.withFirstOptional( output1, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.VANILLA_END_ORE, 1, BlockVanillaEndOre.Type.IRON.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		output1 = ItemHelper.getOre("dustCoal", 1);
    		output2 = ItemHelper.getOre("dustCoal", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.VANILLA_ORE, 1, BlockVanillaOre.Type.COAL.getMetadata()) )
					.withOutput( output1 )
					.withFirstOptional( output2, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.VANILLA_END_ORE, 1, BlockVanillaEndOre.Type.COAL.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		if (NFConfig.END_ORES) {
		        IMaterials materialsApi = AEApi.instance().definitions().materials();
    		
	        	Optional<ItemStack> netherQuartzDust = materialsApi.netherQuartzDust().maybeStack(1);
	        	if (netherQuartzDust.isPresent()) {
		        	output1 = netherQuartzDust.get();
	    			grinder.addRecipe(
	    				builder.withInput( new ItemStack(NFBlocks.VANILLA_END_ORE, 1, BlockVanillaEndOre.Type.QUARTZ.getMetadata()) )
	    					.withOutput( output1 )
	    					.withFirstOptional( output1, (float) 0.9)
	    					.build() );
	        	}
    		}
    		
    		output1 = ItemHelper.getOre("dustCopper", 1);
    		output2 = ItemHelper.getOre("dustCopper", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.TF_ORE, 1, BlockTFOre.Type.COPPER.getMetadata()) )
					.withOutput( output2 )
					.withFirstOptional( output1, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.TF_END_ORE, 1, BlockTFEndOre.Type.COPPER.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		output1 = ItemHelper.getOre("dustTin", 1);
    		output2 = ItemHelper.getOre("dustTin", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.TF_ORE, 1, BlockTFOre.Type.TIN.getMetadata()) )
					.withOutput( output2 )
					.withFirstOptional( output1, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.TF_END_ORE, 1, BlockTFEndOre.Type.TIN.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		output1 = ItemHelper.getOre("dustSilver", 1);
    		output2 = ItemHelper.getOre("dustSilver", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.TF_ORE, 1, BlockTFOre.Type.SILVER.getMetadata()) )
					.withOutput( output2 )
					.withFirstOptional( output1, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.TF_END_ORE, 1, BlockTFEndOre.Type.SILVER.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		output1 = ItemHelper.getOre("dustLead", 1);
    		output2 = ItemHelper.getOre("dustLead", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.TF_ORE, 1, BlockTFOre.Type.LEAD.getMetadata()) )
					.withOutput( output2 )
					.withFirstOptional( output1, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.TF_END_ORE, 1, BlockTFEndOre.Type.LEAD.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		output1 = ItemHelper.getOre("dustAluminum", 1);
    		output2 = ItemHelper.getOre("dustAluminum", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.TF_ORE, 1, BlockTFOre.Type.ALUMINUM.getMetadata()) )
					.withOutput( output2 )
					.withFirstOptional( output1, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.TF_END_ORE, 1, BlockTFEndOre.Type.ALUMINUM.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		output1 = ItemHelper.getOre("dustNickel", 1);
    		output2 = ItemHelper.getOre("dustNickel", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.TF_ORE, 1, BlockTFOre.Type.NICKEL.getMetadata()) )
					.withOutput( output2 )
					.withFirstOptional( output1, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.TF_END_ORE, 1, BlockTFEndOre.Type.NICKEL.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		output1 = ItemHelper.getOre("dustPlatinum", 1);
    		output2 = ItemHelper.getOre("dustPlatinum", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.TF_ORE, 1, BlockTFOre.Type.PLATINUM.getMetadata()) )
					.withOutput( output2 )
					.withFirstOptional( output1, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.TF_END_ORE, 1, BlockTFEndOre.Type.PLATINUM.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		output1 = ItemHelper.getOre("dustIridium", 1);
    		output2 = ItemHelper.getOre("dustIridium", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.TF_ORE, 1, BlockTFOre.Type.IRIDIUM.getMetadata()) )
					.withOutput( output2 )
					.withFirstOptional( output1, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.TF_END_ORE, 1, BlockTFEndOre.Type.IRIDIUM.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		output1 = ItemHelper.getOre("dustMithril", 1);
    		output2 = ItemHelper.getOre("dustMithril", 2);
    		grinder.addRecipe(
				builder.withInput( new ItemStack(NFBlocks.TF_ORE, 1, BlockTFOre.Type.MITHRIL.getMetadata()) )
					.withOutput( output2 )
					.withFirstOptional( output1, (float) 0.9)
					.build() );
    		
    		if (NFConfig.END_ORES)
    			grinder.addRecipe(
    				builder.withInput( new ItemStack(NFBlocks.TF_END_ORE, 1, BlockTFEndOre.Type.MITHRIL.getMetadata()) )
    					.withOutput( output2 )
    					.withFirstOptional( output1, (float) 0.9)
    					.build() );
    		
    		if (NFConfig.AE2_ORES) {
		        IMaterials materialsApi = AEApi.instance().definitions().materials();
		        Optional<ItemStack> certusQuartzDust = materialsApi.certusQuartzDust().maybeStack(1);
	
		        if (certusQuartzDust.isPresent()) {
		        	output1 = certusQuartzDust.get();
			        grinder.addRecipe(
		    	        builder.withInput(new ItemStack(NFBlocks.AE2_ORE, 1, BlockAE2Ore.Type.CERTUS.getMetadata()))
		    		 	.withOutput(output1)
		    		 	.withFirstOptional(output1, (float) 0.9)
		    		 	.build() );

				    grinder.addRecipe(
		    	        builder.withInput(new ItemStack(NFBlocks.AE2_ORE, 1, BlockAE2Ore.Type.CHARGED_CERTUS.getMetadata()) )
		    		 	.withOutput( output1 )
		    		 	.withFirstOptional( output1, (float) 0.9)
		    		 	.build() );

				    grinder.addRecipe(
		    	        builder.withInput(new ItemStack(NFBlocks.AE2_ORE, 1, BlockAE2Ore.Type.END_CERTUS.getMetadata()) )
		    		 	.withOutput(output1)
		    		 	.withFirstOptional(output1, (float) 0.9)
		    		 	.build() );

			        grinder.addRecipe(
		    	        builder.withInput(new ItemStack(NFBlocks.AE2_ORE, 1, BlockAE2Ore.Type.END_CHARGED_CERTUS.getMetadata()) )
		    		 	.withOutput(output1 )
		    		 	.withFirstOptional(output1, (float) 0.9)
		    		 	.build() );
		        }
    		}

    		/*
    		if (NFConfig.EXTRA_ORES) {
		        IMaterials materialsApi = AEApi.instance().definitions().materials();
		        Optional<ItemStack> netherQuartzDust = materialsApi.netherQuartzDust().maybeStack(1);
		        if (netherQuartzDust.isPresent()) {
		        	output = netherQuartzDust.get();
			        grinder.addRecipe(
		    	        builder.withInput(new ItemStack(NFBlocks.EXTRA_ORE, 1, BlockExtraOre.Type.QUARTZ.getMetadata()))
		    		 	.withOutput(output)
		    		 	.withFirstOptional(output, (float) 0.9)
		    		 	.build() );
		        	
			        grinder.addRecipe(
			    	        builder.withInput(new ItemStack(NFBlocks.EXTRA_ORE, 1, BlockExtraOre.Type.END_QUARTZ.getMetadata()))
			    		 	.withOutput(output)
			    		 	.withFirstOptional(output, (float) 0.9)
			    		 	.build() );
			        	
		        }
    		}
*/
		        
    	}
    }
}
