package pharabus.mods.aeperipheral.client;

import net.minecraft.world.World;
import pharabus.mods.aeperipheral.CommonProxy;
import pharabus.mods.aeperipheral.TileEntityAEPeripheral;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }
    

}
