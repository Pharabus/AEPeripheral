package pharabus.mods.aeperipheral;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class AEPeripheralTickHandler 
implements ITickHandler {

	  private static Multimap tilesForInit = LinkedHashMultimap.create();
	  
	  int howofften = 0;

	  @ForgeSubscribe
	  public synchronized void worldUnload(WorldEvent.Unload w)
	  {
	    tilesForInit.removeAll(w.world);
	  }

	  public synchronized void TriggerInit(TileEntityAEPeripheral te)
	  {
	    tilesForInit.put(te.worldObj, te);
	  }
	  
	@Override
	public synchronized void tickStart(EnumSet<TickType> type, Object... tickData) {
		if (this.howofften++ % 900 == 0)
	    {
	     // AppEng.log(new StringBuilder().append("NBT Cache: ").append(Platform.isClient() ? "CLIENT" : "SERVER").append(" = ").append(Platform.sharedTagLoad()).toString());
	    }

	    if (type.contains(TickType.WORLD))
	    {
	      World w = (World)tickData[0];

	      List<TileEntityAEPeripheral> ateJ = new LinkedList<TileEntityAEPeripheral>();
	      ateJ.addAll(tilesForInit.get(w));
	      tilesForInit.get(w).clear();

	      for (TileEntityAEPeripheral ate : ateJ)
	        ate.init();
	    }
	    else if (type.contains(TickType.CLIENT))
	    {
	      World w = aeperipheral.proxy.getClientWorld();

	      List<TileEntityAEPeripheral> ateJ = new LinkedList<TileEntityAEPeripheral>();
	      ateJ.addAll(tilesForInit.get(w));
	      tilesForInit.get(w).clear();

	      for (TileEntityAEPeripheral ate : ateJ)
	        ate.init();
	    }
		
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		 return EnumSet.of(TickType.WORLD, TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "AEPEripheral TE Initialization";
	}

}
