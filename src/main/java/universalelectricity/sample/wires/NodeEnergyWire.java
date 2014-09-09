package universalelectricity.sample.wires;

import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.api.core.grid.IGrid;
import universalelectricity.api.core.grid.INodeProvider;
import universalelectricity.api.core.grid.electric.IEnergyNode;
import universalelectricity.simulator.energy.EnergySimulatedGrid;
import universalelectricity.simulator.grid.LinkedGrid;
import universalelectricity.simulator.grid.component.NetworkNode;

/**
 * Extended version of th conductor node that only connects to energy based tiles
 * @author Darkguardsman
 */
public class NodeEnergyWire extends NetworkNode implements IEnergyNode {

    public NodeEnergyWire(INodeProvider parent)
    {
        super(parent);
    }

    @Override
    public double addEnergy(ForgeDirection from, double wattage, boolean doAdd)
    {
        return 0;
    }

    @Override
    public double removeEnergy(ForgeDirection from, double wattage, boolean doRemove)
    {
        // TODO find energy storage and remove energy
        return 0;
    }

    @Override
    public double getEnergy(ForgeDirection from)
    {
        return 0;
    }

    @Override
    public double getEnergyCapacity(ForgeDirection from)
    {
        return 0;
    }

    @Override
    public void setGrid(IGrid grid)
    {
        if(grid instanceof EnergySimulatedGrid)
        {
           super.setGrid(grid);
        }
    }

    @Override
    public LinkedGrid getGrid()
    {
        if(network == null)
        {
            super.setGrid(new EnergySimulatedGrid(this));
        }
        return super.getGrid();
    }
}
