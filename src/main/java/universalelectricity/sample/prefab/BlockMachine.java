package universalelectricity.sample.prefab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.sample.wires.TileWire;

/**
 * Created by robert on 8/24/2014.
 */
public class BlockMachine extends Block implements ITileEntityProvider
{
    Class< ? extends TileMachine> machineTileClazz;
    protected BlockMachine(Material material, Class< ? extends TileMachine> machineTileClazz)
    {
        super(material);
        this.machineTileClazz = machineTileClazz;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        try
        {
            return machineTileClazz.newInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onNeighborBlockChange(World w, int x, int y, int z, Block b)
    {
        TileEntity tile = w.getTileEntity(x, y, z);
        if(tile instanceof TileMachine)
        {
            ((TileMachine)tile).energyNode.reconstruct();
        }
    }
}
