package tehseph.netherfoundation.compat;

import tehseph.netherfoundation.init.NFBlocks;
import tehseph.netherfoundation.init.NFConfig;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class WailaCompatibility implements IWailaDataProvider {

    public static final WailaCompatibility INSTANCE = new WailaCompatibility();

    public static void register(IWailaRegistrar registrar) {

        registrar.registerStackProvider(INSTANCE, NFBlocks.HELLFISH.getClass());

    }

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {

        Block block = accessor.getBlock();

        if (block == NFBlocks.HELLFISH && NFConfig.HELLFISH_WAILA) return new ItemStack(Blocks.NETHERRACK);

        return ItemStack.EMPTY;

    }

}
