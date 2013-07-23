package pharabus.mods.aeperipheral;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAEPeripheral extends BlockContainer {

    public BlockAEPeripheral(int id) {
        super(id, Material.iron);
        setUnlocalizedName("AEPeripheral");
        setHardness(3.0F);
        setCreativeTab(CreativeTabs.tabDecorations);
        // TODO Auto-generated constructor stub
    }

    @Override
    public TileEntity createTileEntity(World w, int metaData) {
        TileEntityAEPeripheral te = new TileEntityAEPeripheral();

        return te;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return null;
    }

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        icons = new Icon[3];
        int i = 0;

        for (String s : sideNames) {
            icons[i++] = par1IconRegister.registerIcon(String.format(
                    "aeperipheral:AEPEripheralBlock_%s", s));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int i, int j) {

        return icons[sideMapping[i]];
    }

    private static String[] sideNames = { "top", "front", "side" };
    private static int[] sideMapping = { 0, 0, 2, 1, 2, 2, 2 };

}
