package universalelectricity.sample.machines;
import net.minecraft.block.material.Material;
import com.builtbroken.lib.prefab.tile.TileElectricInventory;
/** Battery box that uses internal items for energy storage.
 * Is an example of item charging, discharging, and storage
 *
 * Created by robert on 8/24/2014.
 */
public class TileBatteryBox extends TileElectricInventory
{
    public TileBatteryBox()
    {
        super(Material.wood);
        this.setSizeInventory(4);
    }
}
