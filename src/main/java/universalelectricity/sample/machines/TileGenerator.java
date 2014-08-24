package universalelectricity.sample.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import universalelectricity.sample.prefab.TileMachine;
import universalelectricity.sample.prefab.TileSingleSlotMachine;

/**
 * Created by robert on 8/24/2014.
 */
public class TileGenerator  extends TileSingleSlotMachine
{
    int ticksToBurn = 0;

    public TileGenerator()
{
    super();
    buffer().setCapacity(10000);
    buffer().setMaxReceive(0);
    buffer().setMaxExtract(1000);
}

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if(ticksToBurn > 0)
        {
            ticksToBurn--;
        }
        if(ticksToBurn <= 10 && TileEntityFurnace.isItemFuel(slotStack))
        {
            ticksToBurn += TileEntityFurnace.getItemBurnTime(slotStack);
            slotStack.stackSize--;
            if(slotStack.stackSize <= 0)
            {
                slotStack = null;
            }
        }
        if(ticksToBurn > 0)
        {
            buffer().receiveEnergy();
        }
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
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot == 0 && TileEntityFurnace.isItemFuel(stack);
    }

    @Override
    public String getInventoryName() {
        return "SampleGenerator";
    }
}
