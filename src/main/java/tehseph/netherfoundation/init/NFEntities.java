package tehseph.netherfoundation.init;

import tehseph.netherfoundation.NetherFoundation;
import tehseph.netherfoundation.Reference;
import tehseph.netherfoundation.client.renderer.entity.*;
import tehseph.netherfoundation.entity.monster.*;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NFEntities {

    public static void preInitCommon() {

        int id = 1;

        if (NFConfig.HELLFISH) {

            EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":hellfish"), EntityHellfish.class, Reference.MOD_ID + ".hellfish", id++, NetherFoundation.INSTANCE, 64, 1, true, 0xC63010, 0xFFB030);
            LootTableList.register(EntityHellfish.LOOT_TABLE);

        }

    }

    @SideOnly(Side.CLIENT)
    public static void preInitClient() {

        if (NFConfig.HELLFISH) RenderingRegistry.registerEntityRenderingHandler(EntityHellfish.class, RenderHellfish.RENDER_FACTORY);

    }

}
