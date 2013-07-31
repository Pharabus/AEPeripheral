package pharabus.mods.aeperipheral;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import appeng.api.Blocks;
import dan200.computer.api.IHostedPeripheral;
import dan200.turtle.api.ITurtleAccess;
import dan200.turtle.api.ITurtleUpgrade;
import dan200.turtle.api.TurtleSide;
import dan200.turtle.api.TurtleUpgradeType;
import dan200.turtle.api.TurtleVerb;

public class AEPeripheralTurtleUpgrade implements ITurtleUpgrade {

    @Override
    public int getUpgradeID() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getAdjective() {
        // TODO Auto-generated method stub
        return "AE Turtle";
    }

    @Override
    public TurtleUpgradeType getType() {
        // TODO Auto-generated method stub
        return TurtleUpgradeType.Peripheral;
    }

    @Override
    public ItemStack getCraftingItem() {
        // TODO Auto-generated method stub
        return Blocks.blkColorlessCable.copy();
    }

    @Override
    public boolean isSecret() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IHostedPeripheral createPeripheral(ITurtleAccess turtle,
            TurtleSide side) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean useTool(ITurtleAccess turtle, TurtleSide side,
            TurtleVerb verb, int direction) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Icon getIcon(ITurtleAccess turtle, TurtleSide side) {
        // TODO Auto-generated method stub
        return null;
    }

}
