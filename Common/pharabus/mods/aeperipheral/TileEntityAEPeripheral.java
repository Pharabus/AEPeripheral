package pharabus.mods.aeperipheral;

import java.util.HashMap;
import java.util.Map;

import appeng.api.IAEItemStack;
import appeng.api.IItemList;
import appeng.api.Util;
import appeng.api.WorldCoord;
import appeng.api.events.GridTileLoadEvent;
import appeng.api.events.GridTileUnloadEvent;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.tiles.IGridTileEntity;
import appeng.api.me.util.ICraftRequest;
import appeng.api.me.util.IGridInterface;
import appeng.api.me.util.IMEInventory;
import dan200.computer.api.IComputerAccess;
import dan200.computer.api.IPeripheral;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import java.util.List;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityAEPeripheral extends TileEntity
implements IPeripheral, IGridMachine {
	private boolean isValidFlag = true;
	public boolean hasPower = false;
	private IGridInterface myGrid;
	public int gIdx = 0;
	protected boolean isLoaded = false;
	
	@Override
	public void validate()
	{
		super.validate();

	    if (!this.isLoaded)
	    {
	      if (AEPeripheralUtil.isClient())
	    	  aeperipheral.AEPeripheralGenericTick_client.TriggerInit(this);
	      else
	    	  aeperipheral.AEPeripheralGenericTick_server.TriggerInit(this);
	    }
	}
	
	@Override
	public void invalidate()
	{
		super.invalidate();

	    if (this.isLoaded)
	    {
	      this.isLoaded = false;
	      terminate();
	    }
	}
	 
	@Override
	public String getType() {
		
		return "AEPeripheral";
	}

	@Override
	public String[] getMethodNames() {
		
		return new String[] { "GetInventory", "GetCraftables", "Craft"};
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, int method,
			Object[] arguments) throws Exception {
	
		
		Map ret = new HashMap();
		if(!this.hasPower)
		{
			return new Object[] {"No Power"};
		}
		if(this.myGrid == null)
		{
			return new Object[] {"No Contoller"};
		}
		if(this.myGrid != null)
		{
		
			 IGridInterface gi = this.getGrid();
			 IMEInventory ginv = null;
			 IItemList list = null;
			switch(method)
			{
			case 0:
				  ginv = gi.getCellArray();
				  list = ginv.getAvailableItems();
				 for(IAEItemStack item : list ){
					  ret.put(item.getItemStack().getDisplayName(), item.getStackSize());
					}
				 break;
			case 1:
				ginv = gi.getCraftableArray();
				list = ginv.getAvailableItems();
				 for(IAEItemStack item : list ){
					  ret.put(item.getItemStack().getDisplayName(), item.getItemID());
					}
				break;
			case 2:
				int Id = (int) Math.round((Double)arguments[0]);
				int howMany = (int) Math.round((Double)arguments[1]);
				ICraftRequest request = gi.craftingRequest(new ItemStack(Id,howMany,0));	
				break;
			}     
			
		}		
		return new Object [] {ret};
	}

	@Override
	public boolean canAttachToSide(int side) {
		return true;
	}

	@Override
	public void attach(IComputerAccess computer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detach(IComputerAccess computer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WorldCoord getLocation() {
		// TODO Auto-generated method stub
		return new WorldCoord(this.xCoord, this.yCoord, this.zCoord);
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return this.isValidFlag;
	}

	@Override
	public void setPowerStatus(boolean _hasPower) {
		FMLLog.log(Level.INFO, "AEPeripheral setPowerStatus called _hasPower is" + _hasPower);
		if (this.hasPower != _hasPower)
	    {
	      this.hasPower = _hasPower;
	      markForUpdate();
	    }
		
	}

	@Override
	public boolean isPowered() {
		
		 return this.hasPower;
	}

	@Override
	public IGridInterface getGrid() {
		FMLLog.log(Level.INFO, "AEPeripheral getGrid called my grid is" + this.myGrid.isValid());
		return this.myGrid.isValid() ? this.myGrid : null;
	}

	@Override
	public void setGrid(IGridInterface gi) {
		 FMLLog.log(Level.INFO, "AEPeripheral setGrid called");
		if (gi != this.myGrid)
	    {
	      this.myGrid = gi;

	      if (gi != null)
	        this.gIdx = gi.getGridIndex();
	      else {
	        this.gIdx = 0;
	      }
	      if (this.myGrid == null) setPowerStatus(false);
	      markForUpdate();
	    }	
	}

	@Override
	public World getWorld() {
		
		return this.worldObj;
	}

	public void markForUpdate()
	  {
		 if (AEPeripheralUtil.isClient()) {
		      this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		    }		  
		    else
		      this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
	  }

	@Override
	public float getPowerDrainPerTick() {
		
		return 1.0F;
	}

	public void init()
	  {
	    if (this.isLoaded) return;

	    this.isLoaded = true;

	
	 
	      MinecraftForge.EVENT_BUS.post(new GridTileLoadEvent((IGridTileEntity)this, this.worldObj, getLocation()));
	    

	    this.worldObj.updateAllLightTypes(this.xCoord, this.yCoord, this.zCoord);
	  }

	  protected void terminate()
	  {
	    this.isLoaded = false;
	    setGrid(null);
	    MinecraftForge.EVENT_BUS.post(new GridTileUnloadEvent((IGridTileEntity)this, this.worldObj, getLocation()));

	  }
	
	
}
