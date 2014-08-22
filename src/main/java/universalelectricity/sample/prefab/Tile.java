package universalelectricity.sample.prefab;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.transform.vector.IVectorWorld;

/**
 * Simple prefab for all tiles in this mod
 * @author Darkguardsman
 */
public class Tile extends TileEntity implements IVectorWorld
{
    protected long ticks = 0L;

    /** Called on the first world tick of this tile */
    public void init()
    {

    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if(ticks == 0)
        {
            init();
        }
        else if(ticks + 1 == Long.MAX_VALUE)
        {
            ticks = 0;
        }
        ticks++;
    }

    public World world()
    {
        return getWorldObj();
    }

    public int xi() { return xCoord;}
    public int yi() { return yCoord;}
    public int zi() { return zCoord;}

    @Override
    public double z() {
        return zi();
    }

    @Override
    public double x() {
        return xi();
    }

    @Override
    public double y() {
        return yi();
    }

    public IVectorWorld pos()
    {
        return (IVectorWorld)this;
    }
}
