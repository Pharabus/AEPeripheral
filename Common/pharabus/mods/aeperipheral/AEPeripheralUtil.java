package pharabus.mods.aeperipheral;

import cpw.mods.fml.common.FMLCommonHandler;

public class AEPeripheralUtil {
    public static boolean isClient() {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    public static boolean isServer() {
        return !isClient();
    }

}
