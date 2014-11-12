package universalelectricity.sample.machines;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import resonant.lib.content.prefab.java.TileElectricInventory;

/**
 * Created by robert on 8/24/2014.
 */
public class TileGenerator extends TileElectricInventory
{
    int ticksToBurn = 0;

    public TileGenerator()
    {
        super(Material.rock);
        setCapacity(10000);
        setMaxReceive(0);
        setMaxExtract(1000);
    }

    @Override
    public void update()
    {
        super.update();
        if (ticksToBurn > 0)
        {
            ticksToBurn--;
        }
        if (ticksToBurn <= 10 && TileEntityFurnace.isItemFuel(fuelStack()))
        {
            ticksToBurn += TileEntityFurnace.getItemBurnTime(fuelStack());
            fuelStack().stackSize--;
            if (fuelStack().stackSize <= 0)
            {
                setInventorySlotContents(0, null);
            }
        }
        if (ticksToBurn > 0)
        {
            getEnergyStorage().receiveEnergy();
        }
    }

    /** @return stack in slot one */
    public ItemStack fuelStack()
    {
        return getStackInSlot(0);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setInteger("burnTicks", ticksToBurn);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        ticksToBurn = nbt.getInteger("burnTicks");
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        return slot == 0 && TileEntityFurnace.isItemFuel(stack);
    }

    @Override
    public String getInventoryName()
    {
        return "SampleGenerator";
    }
}
