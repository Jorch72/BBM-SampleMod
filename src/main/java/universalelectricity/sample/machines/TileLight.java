package universalelectricity.sample.machines;

import universalelectricity.sample.prefab.TileMachine;

/**
 * Created by robert on 8/24/2014.
 */
public class TileLight extends TileMachine
{
    public boolean isPowered = false;

    public TileLight()
    {
        super();
        buffer().setMaxReceive(2);
        buffer().setMaxExtract(1);
        buffer().setCapacity(10);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        isPowered = false;
        if(buffer().checkExtract())
        {
            buffer().extractEnergy();
            isPowered = true;
        }
    }
}
