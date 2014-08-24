package universalelectricity.sample.wires;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.api.core.grid.INode;
import universalelectricity.api.core.grid.INodeProvider;
import universalelectricity.api.core.grid.electric.IEnergyNode;
import universalelectricity.core.grid.node.NodeEnergy;
import universalelectricity.sample.prefab.Tile;

/**
 * Created by robert on 8/22/2014.
 */
public class TileWire extends Tile implements INodeProvider
{
    public NodeEnergy energyNode;

    public TileWire()
    {
        energyNode = new NodeEnergy(this);
    }

    @Override
    public INode getNode(Class<? extends INode> nodeType, ForgeDirection from)
    {
        if(IEnergyNode.class.isAssignableFrom(nodeType) && energyNode.canConnect(from))
        {
            return energyNode;
        }
        return null;
    }

    @Override
    public void start()
    {
        energyNode.reconstruct();
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        energyNode.deconstruct();
    }
}
