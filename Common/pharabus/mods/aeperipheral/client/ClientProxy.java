package pharabus.mods.aeperipheral.client;

import net.minecraft.world.World;
import pharabus.mods.aeperipheral.CommonProxy;
import cpw.mods.fml.client.FMLClientHandler;

public class ClientProxy extends CommonProxy {
    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }
    

}
