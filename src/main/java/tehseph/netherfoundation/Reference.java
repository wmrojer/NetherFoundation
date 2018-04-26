package tehseph.netherfoundation;

import tehseph.netherfoundation.init.NFBlocks;

import cofh.CoFHCore;
import cofh.cofhworld.CoFHWorld;
import cofh.core.gui.CreativeTabCore;
import cofh.thermalfoundation.ThermalFoundation;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Reference {

    public static final boolean DEBUG_MODE        = false;

    public static final String MOD_ID             = "netherfoundation";
    public static final String NAME               = "Nether Foundation";
    public static final String VERSION            = "1.0.0";
    public static final String MC_VERSIONS        = "[1.12.2, 1.13)";

    public static final String UPDATE_JSON        = "https://github.com/TehSeph/NetherFoundation/raw/master/update.json";

    public static final String GUI_FACTORY        = "tehseph.netherfoundation.client.gui.ConfigGuiFactory";
    public static final String CLIENT_PROXY       = "tehseph.netherfoundation.proxy.ClientProxy";
    public static final String SERVER_PROXY       = "tehseph.netherfoundation.proxy.ServerProxy";

    public static final String DEPENDENCIES       = "required-after:forge@[14.23.3.2673,);"
                                                  + CoFHCore.VERSION_GROUP
                                                  + CoFHWorld.VERSION_GROUP
                                                  + ThermalFoundation.VERSION_GROUP
                                                  + "after:thermalexpansion;"
                                                  + ""; // leave last to avoid useless git diffs

    public static final CreativeTabCore CREATIVE_TAB = new CreativeTabCore(MOD_ID) {

        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack() {
            return new ItemStack(NFBlocks.NETHER_ORE);
        }

    };

}
