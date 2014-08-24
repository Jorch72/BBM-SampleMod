package universalelectricity.sample.prefab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.api.EnergyStorage;
import universalelectricity.api.core.grid.INode;
import universalelectricity.api.core.grid.INodeProvider;
import universalelectricity.api.core.grid.electric.IEnergyNode;
import universalelectricity.core.grid.node.NodeEnergy;

/**
 * Created by robert on 8/24/2014.
 */
public class TileMachine extends Tile implements INodeProvider
{
    public NodeEnergy energyNode;

    public TileMachine()
    {
        energyNode = new NodeEnergy(this);
    }

    public EnergyStorage buffer()
    {
        return energyNode.buffer();
    }

    public double getEnergy()
    {
        return buffer().getEnergy();
    }

    public double getCapacity()
    {
        return buffer().getEnergyCapacity();
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
