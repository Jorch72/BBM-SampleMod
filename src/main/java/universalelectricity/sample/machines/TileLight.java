package universalelectricity.sample.machines;

import net.minecraft.block.material.Material;
import resonant.lib.prefab.tile.TileElectric;

/**
 * Created by robert on 8/24/2014.
 */
public class TileLight extends TileElectric
{
    public boolean isPowered = false;

    public TileLight()
    {
        super(Material.rock);
        setMaxReceive(2);
        setMaxExtract(1);
        setCapacity(10);
    }

    @Override
    public void update()
    {
        super.update();
        isPowered = false;
        if(getEnergyStorage().checkExtract())
        {
            getEnergyStorage().extractEnergy();
            isPowered = true;
        }
    }
}
