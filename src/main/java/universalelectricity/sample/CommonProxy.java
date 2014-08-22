package universalelectricity.sample;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by robert on 8/22/2014.
 */
public class CommonProxy implements IGuiHandler
{
    /** Called during preload phase of the mod */
    public void preInit()
    {

    }

    /** Called during load phase of the mod */
    public void init()
    {

    }

    /** Called during postload phase of the mod */
    public void postInit()
    {

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }
}
