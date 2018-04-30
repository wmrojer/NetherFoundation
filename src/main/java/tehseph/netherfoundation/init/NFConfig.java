package tehseph.netherfoundation.init;

import tehseph.netherfoundation.NetherFoundation;
import tehseph.netherfoundation.Reference;

import cofh.core.init.CoreProps;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.util.Loader;

import java.io.File;

public class NFConfig {

    public static boolean ANGRY_PIGMEN = true;
    public static int     ANGRY_PIGMEN_RANGE = 32;
    public static boolean ANGRY_PIGMEN_SILKTOUCH = true;

    public static boolean EXPLOSIONS = true;
    public static double  EXPLOSIONS_CHANCE = 0.125D;
    public static boolean EXPLOSIONS_FORTUNE = true;
    public static boolean EXPLOSIONS_SILKTOUCH = true;

    public static boolean HELLFISH = true;
    public static double  HELLFISH_ATTACK_DAMAGE = 1.0D;
    public static double  HELLFISH_KNOCKBACK_RESISTANCE = 1.0D;
    public static double  HELLFISH_MAX_HEALTH = 10.0D;
    public static double  HELLFISH_MOVEMENT_SPEED = 0.33D;
    public static boolean HELLFISH_SET_FIRE = true;
    public static boolean HELLFISH_WAILA = true;

    public static boolean PROCESSING_FURNACE = true;
    public static boolean PROCESSING_INDUCTION_SMELTER = true;
    public static boolean PROCESSING_PETROTHEUM = true;
    public static boolean PROCESSING_PULVERIZER = true;
    public static boolean PROCESSING_PYROTHEUM = true;
    public static boolean PROCESSING_SMELT_TO_ORES = true;

    public static void preInitCommon(File configFile) {

        NetherFoundation.CONFIG = new Configuration(configFile, true);
        syncConfig(true);
        addWorldGeneration();

    }

    @SideOnly(Side.CLIENT)
    public static void preInitClient() {
        MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
    }

    public static void postInitCommon() {
        if (NetherFoundation.CONFIG.hasChanged()) NetherFoundation.CONFIG.save();
    }

    private static void syncConfig(boolean loadFromFile) {

        if (loadFromFile) NetherFoundation.CONFIG.load();

        String category;
        String comment;
        Property property;

        category = "AngryPigmen";

        comment = "If TRUE, zombie pigmen will become hostile towards the player when nearby ores are mined.";
        property = NetherFoundation.CONFIG.get(category, "AngryPigmen", ANGRY_PIGMEN, comment);
        ANGRY_PIGMEN = property.getBoolean(ANGRY_PIGMEN);

        comment = "Sets how many blocks away from an ore a zombie pigman can be angered from. (Capped at 64 blocks to prevent lag.)";
        property = NetherFoundation.CONFIG.get(category, "AngryPigmenRange", ANGRY_PIGMEN_RANGE, comment, 0, 64);
        ANGRY_PIGMEN_RANGE = property.getInt(ANGRY_PIGMEN_RANGE);

        comment = "If TRUE, zombie pigmen don't become hostile when ores are mined with Silktouch.";
        property = NetherFoundation.CONFIG.get(category, "AngryPigmenSilktouch", ANGRY_PIGMEN_SILKTOUCH, comment);
        ANGRY_PIGMEN_SILKTOUCH = property.getBoolean(ANGRY_PIGMEN_SILKTOUCH);

        category = "Explosions";

        comment = "If TRUE, nether ores have a chance to explode when mined.";
        property = NetherFoundation.CONFIG.get(category, "Explosions", EXPLOSIONS, comment);
        EXPLOSIONS = property.getBoolean(EXPLOSIONS);

        comment = "Sets the percent chance nether ores will explode when mined. (1.0 = 100%, 0.01 = 1%)";
        property = NetherFoundation.CONFIG.get(category, "ExplosionsChance", EXPLOSIONS_CHANCE, comment, 0.0D, 1.0D);
        EXPLOSIONS_CHANCE = property.getDouble(EXPLOSIONS_CHANCE);

        comment = "If TRUE, ore explosions have a 2X higher chance when mined with Fortune.";
        property = NetherFoundation.CONFIG.get(category, "ExplosionsFortune", EXPLOSIONS_FORTUNE, comment);
        EXPLOSIONS_FORTUNE = property.getBoolean(EXPLOSIONS_FORTUNE);

        comment = "If TRUE, ore explosions do not trigger when mined with Silktouch.";
        property = NetherFoundation.CONFIG.get(category, "ExplosionsSilktouch", EXPLOSIONS_SILKTOUCH, comment);
        EXPLOSIONS_SILKTOUCH = property.getBoolean(EXPLOSIONS_SILKTOUCH);

        category = "Hellfish";

        comment = "If TRUE, a more dangerous nether version of Silverfish may spawn from breaking Netherrack.";
        property = NetherFoundation.CONFIG.get(category, "Hellfish", HELLFISH, comment).setRequiresMcRestart(true);
        HELLFISH = property.getBoolean(HELLFISH);

        comment = "Sets the Attack Damage of Hellfish. (Silverfish have an Attack Damage of 1.0)";
        property = NetherFoundation.CONFIG.get(category, "HellfishAttackDamage", HELLFISH_ATTACK_DAMAGE, comment, 0.0D, 2048.0D).setRequiresMcRestart(true);
        HELLFISH_ATTACK_DAMAGE = property.getDouble(HELLFISH_ATTACK_DAMAGE);

        comment = "Sets the Knockback Resistance of Hellfish. (Silverfish have a Knockback Resistance of 0.0)";
        property = NetherFoundation.CONFIG.get(category, "HellfishKnockbackResistance", HELLFISH_KNOCKBACK_RESISTANCE, comment, 0.0D, 1.0D).setRequiresMcRestart(true);
        HELLFISH_KNOCKBACK_RESISTANCE = property.getDouble(HELLFISH_KNOCKBACK_RESISTANCE);

        comment = "Sets the Maximum Health of Hellfish. (Silverfish have a Maximum Health of 8.0)";
        property = NetherFoundation.CONFIG.get(category, "HellfishMaxHealth", HELLFISH_MAX_HEALTH, comment, Float.MIN_VALUE, 1024.0D).setRequiresMcRestart(true);
        HELLFISH_MAX_HEALTH = property.getDouble(HELLFISH_MAX_HEALTH);

        comment = "Sets the Movement Speed of Hellfish. (Silverfish have a Movement Speed of 0.25)";
        property = NetherFoundation.CONFIG.get(category, "HellfishMovementSpeed", HELLFISH_MOVEMENT_SPEED, comment, 0.0D, 1024.0D).setRequiresMcRestart(true);
        HELLFISH_MOVEMENT_SPEED = property.getDouble(HELLFISH_MOVEMENT_SPEED);

        comment = "If TRUE, being attacked by Hellfish sets the player on fire.";
        property = NetherFoundation.CONFIG.get(category, "HellfishSetFire", HELLFISH_SET_FIRE, comment).setRequiresMcRestart(true);
        HELLFISH_SET_FIRE = property.getBoolean(HELLFISH_SET_FIRE);

        comment = "If TRUE, Hellfish spawn blocks are hidden from WAILA.";
        property = NetherFoundation.CONFIG.get(category, "HellfishWAILA", HELLFISH_WAILA, comment).setRequiresMcRestart(true);
        HELLFISH_WAILA = property.getBoolean(HELLFISH_WAILA);

        category = "Processing";

        comment = "If TRUE, enables Furnace (and TE Redstone Furnace) recipes for nether ores.";
        property = NetherFoundation.CONFIG.get(category, "FurnaceRecipes", PROCESSING_FURNACE, comment).setRequiresMcRestart(true);
        PROCESSING_FURNACE = property.getBoolean(PROCESSING_FURNACE);

        comment = "If TRUE, enables TE Induction Smelter recipes for nether ores.";
        property = NetherFoundation.CONFIG.get(category, "InductionSmelterRecipes", PROCESSING_INDUCTION_SMELTER, comment).setRequiresMcRestart(true);
        PROCESSING_INDUCTION_SMELTER = property.getBoolean(PROCESSING_INDUCTION_SMELTER);

        comment = "If TRUE, enables TF Petrotheum recipes for nether ores.";
        property = NetherFoundation.CONFIG.get(category, "PetrotheumRecipes", PROCESSING_PETROTHEUM, comment).setRequiresMcRestart(true);
        PROCESSING_PETROTHEUM = property.getBoolean(PROCESSING_PETROTHEUM);

        comment = "If TRUE, enables TE Pulverizer recipes for nether ores.";
        property = NetherFoundation.CONFIG.get(category, "PulverizerRecipes", PROCESSING_PULVERIZER, comment).setRequiresMcRestart(true);
        PROCESSING_PULVERIZER = property.getBoolean(PROCESSING_PULVERIZER);

        comment = "If TRUE, enables TF Pyrotheum recipes for nether ores.";
        property = NetherFoundation.CONFIG.get(category, "PyrotheumRecipes", PROCESSING_PYROTHEUM, comment).setRequiresMcRestart(true);
        PROCESSING_PYROTHEUM = property.getBoolean(PROCESSING_PYROTHEUM);

        comment = "If TRUE, nether ores will smelt to their normal variants. (Nether Coal Ore => 2x Coal Ore)\n"
                + "If FALSE, nether ores will smelt to ingots or some other appropriate item. (Nether Coal Ore => 2x Coal)";
        property = NetherFoundation.CONFIG.get(category, "SmeltToOres", PROCESSING_SMELT_TO_ORES, comment).setRequiresMcRestart(true);
        PROCESSING_SMELT_TO_ORES = property.getBoolean(PROCESSING_SMELT_TO_ORES);

        if (NetherFoundation.CONFIG.hasChanged()) NetherFoundation.CONFIG.save();

    }

    private static void addWorldGeneration() {

        File worldGenFile;
        String worldGenPath = "assets/" + Reference.MOD_ID + "/world/";
        String worldGenOre = "04_netherfoundation_ores.json";

        worldGenFile = new File(CoreProps.configDir, "/cofh/world/" + worldGenOre);

        if (!worldGenFile.exists()) {

            try {

                worldGenFile.createNewFile();
                FileUtils.copyInputStreamToFile(Loader.getResource(worldGenPath + worldGenOre, null).openStream(), worldGenFile);

            } catch (Throwable error) {

                error.printStackTrace();

            }

        }

    }

    public static class ConfigEventHandler {

        @SubscribeEvent
        public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {

            if (event.getModID().equals(Reference.MOD_ID) && !event.isWorldRunning()) syncConfig(false);

        }

    }

}
