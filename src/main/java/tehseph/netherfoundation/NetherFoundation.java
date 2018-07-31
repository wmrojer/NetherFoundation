package tehseph.netherfoundation;

import tehseph.netherfoundation.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(   modid                     = Reference.MOD_ID,
        name                      = Reference.NAME,
        version                   = Reference.VERSION,
        dependencies              = Reference.DEPENDENCIES,
        acceptedMinecraftVersions = Reference.MC_VERSIONS,
        updateJSON                = Reference.UPDATE_JSON,
        guiFactory                = Reference.GUI_FACTORY,
        useMetadata               = true
)
public class NetherFoundation {

    @Instance(Reference.MOD_ID)
    public static NetherFoundation INSTANCE;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY, modId = Reference.MOD_ID)
    public static CommonProxy PROXY;

    public static final Logger LOG = LogManager.getLogger(Reference.NAME);
    public static Configuration CONFIG;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PROXY.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PROXY.postInit(event);
    }

}
