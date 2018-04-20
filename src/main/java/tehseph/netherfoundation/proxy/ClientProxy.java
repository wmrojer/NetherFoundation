package tehseph.netherfoundation.proxy;

import tehseph.netherfoundation.init.*;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {

		super.preInit(event);

		NFConfig.preInitClient();
        NFBlocks.preInitClient();
        NFEntities.preInitClient();

	}

	@Override
	public void init(FMLInitializationEvent event) {
	    super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
	    super.postInit(event);
	}

}
