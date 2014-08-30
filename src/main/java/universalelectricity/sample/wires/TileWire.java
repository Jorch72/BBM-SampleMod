package universalelectricity.sample.wires;

import universalelectricity.api.core.grid.INodeProvider;
import universalelectricity.core.grid.node.NodeConductor;
import universalelectricity.core.grid.node.NodeEnergy;
import universalelectricity.sample.prefab.TileMachine;

/**
 * Created by robert on 8/22/2014.
 */
public class TileWire extends TileMachine
{
    public TileWire()
    {
        energyNode = new NodeEnergyWire(this);
    }
}
