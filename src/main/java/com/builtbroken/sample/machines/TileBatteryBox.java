package com.builtbroken.sample.machines;
import com.builtbroken.mc.prefab.tile.TileModuleMachine;
import net.minecraft.block.material.Material;
/** Battery box that uses internal items for energy storage.
 * Is an example of item charging, discharging, and storage
 *
 * Created by robert on 8/24/2014.
 */
public class TileBatteryBox extends TileModuleMachine
{
    public TileBatteryBox()
    {
        super("BatteryBox", Material.wood);
    }
}
