package pharabus.mods.aeperipheral;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAEPeripheral extends BlockContainer {

    public BlockAEPeripheral(int id) {
        super(id, Material.iron);
        setUnlocalizedName("AEPeripheral");
        setHardness(3.0F);
        setCreativeTab(CreativeTabs.tabBlock);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack)
    {
           
        int facing = MathHelper.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        
        if (facing == 0)
        {
            world.setBlockMetadataWithNotify(i, j, k, 4, 2);
        }

        if (facing == 1)
        {
            world.setBlockMetadataWithNotify(i, j, k, 10, 2);
        }

        if (facing == 2)
        {
            world.setBlockMetadataWithNotify(i, j, k, 6, 2);
        }

        if (facing == 3)
        {
            world.setBlockMetadataWithNotify(i, j, k, 8, 2);
        }    
    }

    @Override
    public TileEntity createTileEntity(World w, int metaData) {
        TileEntityAEPeripheral te = new TileEntityAEPeripheral();

        return te;
    }

    @SideOnly(Side.CLIENT)
    private boolean IsActive(int meta)
    {
        return meta % 2 == 1;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        // TODO Auto-generated method stub
        return null;
    }

    @SideOnly(Side.CLIENT)
    private Icon IconTop;
    @SideOnly(Side.CLIENT)
    private Icon IconFront;
    @SideOnly(Side.CLIENT)
    private Icon IconFrontEnabled;

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int i, int j)
    {
        if(i == 1 || i == 0)
        {
            return this.IconTop;
        }
        else if(i != j /2)
        {
            return this.blockIcon;
        }
        else
        {
            if(this.IsActive(j))
            {
                return this.IconFrontEnabled;
            }
            else
            {
                return this.IconFront;
            }
        }      
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
           this.blockIcon = par1IconRegister.registerIcon( "aeperipheral:AEPEripheralBlock_side");
           this.IconTop = par1IconRegister.registerIcon( "aeperipheral:AEPEripheralBlock_top");
           this.IconFront = par1IconRegister.registerIcon( "aeperipheral:AEPEripheralBlock_front");
           this.IconFrontEnabled = par1IconRegister.registerIcon( "aeperipheral:AEPEripheralBlock_frontEnabled");
        }
  }

  

