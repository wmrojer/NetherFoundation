package tehseph.netherfoundation.init;

import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.Loader;

public class NFCompat {

    public static void preInitCommon() {

        if (Loader.isModLoaded("waila")) {

            FMLInterModComms.sendMessage("waila", "register", "tehseph.netherfoundation.compat.WailaCompatibility.register");

        }

    }

}
