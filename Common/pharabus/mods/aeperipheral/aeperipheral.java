package pharabus.mods.aeperipheral;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;
import appeng.api.Blocks;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "AEPeripheral", name = "Appled Energistics Peripheral", dependencies = "required-after:Forge@[9.10,);required-after:FML@[6.2,);required-after:AppliedEnergistics;required-after:ComputerCraft")
@NetworkMod(versionBounds = "[1.0,)", clientSideRequired = true, serverSideRequired = true)
public class aeperipheral {

    public static BlockAEPeripheral AEPeripheralblock;
    public static AEPeripheralTickHandler AEPeripheralGenericTick_client;
    public static AEPeripheralTickHandler AEPeripheralGenericTick_server;

    @SidedProxy(clientSide = "pharabus.mods.aeperipheral.client.ClientProxy", serverSide = "pharabus.mods.aeperipheral.CommonProxy")
    public static CommonProxy proxy;

    @Instance("AEPeripheral")
    public static aeperipheral instance;

    private int blockId;

   

   @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Version.init(event.getVersionProperties());
        event.getModMetadata().version = Version.fullVersionString();
        Configuration cfg = new Configuration(
                event.getSuggestedConfigurationFile());

        try {
            cfg.load();
            blockId = cfg.getBlock("AEPeripheral", 976).getInt(976);

        } catch (Exception e) {
            FMLLog.log(Level.SEVERE, e,
                    "AEPeripheral has a problem loading it's configuration");
        } finally {
            cfg.save();
        }
    }

   @EventHandler
    public void load(FMLInitializationEvent evt) {
        AEPeripheralblock = new BlockAEPeripheral(blockId);
        GameRegistry.registerBlock(AEPeripheralblock,
                "AEPeripheral.BlockAEPeripheral");
        LanguageRegistry.instance().addNameForObject(AEPeripheralblock,
                "en_US", "AE Peripheral");
        GameRegistry.registerTileEntity(TileEntityAEPeripheral.class,
                "AEPeripheral");

        AEPeripheralGenericTick_server = new AEPeripheralTickHandler();
        TickRegistry.registerTickHandler(AEPeripheralGenericTick_server,
                Side.SERVER);
        MinecraftForge.EVENT_BUS.register(AEPeripheralGenericTick_server);

        if (AEPeripheralUtil.isClient()) {
            AEPeripheralGenericTick_client = new AEPeripheralTickHandler();
            TickRegistry.registerTickHandler(AEPeripheralGenericTick_client,
                    Side.CLIENT);
            MinecraftForge.EVENT_BUS.register(AEPeripheralGenericTick_client);
        }

        ShapedOreRecipe oreRecipe = new ShapedOreRecipe(new ItemStack(
                AEPeripheralblock),
                new Object[] { "AAA", "BCA", "AAA", 'A', Block.stone, 'B',
                        Blocks.blkColorlessCable, 'C', Item.redstone });
        GameRegistry.addRecipe(oreRecipe);
    }

   @EventHandler
    public void modsLoaded(FMLPostInitializationEvent evt) {
    }

}
