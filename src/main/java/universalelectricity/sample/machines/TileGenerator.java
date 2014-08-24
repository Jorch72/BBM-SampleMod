package universalelectricity.sample.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import universalelectricity.sample.prefab.TileMachine;

/**
 * Created by robert on 8/24/2014.
 */
public class TileGenerator  extends TileMachine implements ISidedInventory
{
    ItemStack fuelStack = null;
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
        if(ticksToBurn <= 10 && TileEntityFurnace.isItemFuel(fuelStack))
        {
            ticksToBurn += TileEntityFurnace.getItemBurnTime(fuelStack);
            fuelStack.stackSize--;
            if(fuelStack.stackSize <= 0)
            {
                fuelStack = null;
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
        if(fuelStack != null)
            nbt.setTag("fuelStack", fuelStack.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        ticksToBurn = nbt.getInteger("burnTicks");
        if(nbt.hasKey("fuelStack"))
            fuelStack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("fuelStack"));
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return slot == 0 && TileEntityFurnace.isItemFuel(stack) && (stack.isItemEqual(fuelStack) || fuelStack == null);
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
    {
        return true;
    }

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        if(slot == 0)
        {
            return fuelStack;
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
    {
        //TODO add code to this section once params are found
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if(slot == 0)
        {
            return fuelStack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        if(slot == 0)
        {
            fuelStack = stack;
        }
    }

    @Override
    public String getInventoryName() {
        return "SampleGenerator";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot == 0 && TileEntityFurnace.isItemFuel(stack);
    }
}
