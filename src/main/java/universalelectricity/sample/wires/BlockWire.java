package universalelectricity.sample.wires;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Simple wire block class
 * @author Darkguardsman
 */
public class BlockWire extends Block implements ITileEntityProvider
{
    public BlockWire()
    {
        super(Material.cloth);
        setBlockBounds(0.3f, 0.3f, 0.3f, 0.7f, 0.7f, 0.7f);
        setBlockName("blockWire");
    }

    @SideOnly(Side.CLIENT) @Override
    public IIcon getIcon(int side, int meta)
    {
        return Blocks.obsidian.getIcon(side, meta);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileWire();
    }

    @Override
    public void onNeighborBlockChange(World w, int x, int y, int z, Block b)
    {
        TileEntity tile = w.getTileEntity(x, y, z);
        if(tile instanceof TileWire)
        {
            ((TileWire)tile).energyNode.reconstruct();
        }
    }
}
