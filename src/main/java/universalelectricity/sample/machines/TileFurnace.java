package universalelectricity.sample.machines;

import universalelectricity.sample.prefab.TileMachine;

/**
 * Created by robert on 8/24/2014.
 */
public class TileFurnace extends TileMachine
{
    public TileFurnace()
    {
        super();
        buffer().setCapacity(10000);
        buffer().setMaxReceive(500);
        buffer().setMaxExtract(0);
    }
}
