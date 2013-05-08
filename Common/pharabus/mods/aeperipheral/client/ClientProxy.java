package pharabus.mods.aeperipheral.client;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.world.World;
import pharabus.mods.aeperipheral.CommonProxy;

public class ClientProxy extends CommonProxy{
	 @Override
    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }
}
