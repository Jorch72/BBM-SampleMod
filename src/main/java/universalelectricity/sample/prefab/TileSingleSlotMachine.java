package universalelectricity.sample.prefab;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 * Created by robert on 8/24/2014.
 */
public abstract class TileSingleSlotMachine extends TileMachine implements ISidedInventory
{
    protected ItemStack slotStack = null;

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        if(slotStack != null)
            nbt.setTag("slotStack", slotStack.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        if(nbt.hasKey("slotStack"))
            slotStack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("slotStack"));
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return isItemValidForSlot(slot, stack);
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
            return slotStack;
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int sum)
    {
        if(slot == 0 && slotStack != null)
        {
            ItemStack stack;
            if(slotStack.stackSize <= sum)
            {
                stack = slotStack;
                slotStack = null;
                return stack;
            }else
            {
                stack = slotStack.splitStack(sum);
                if(slotStack.stackSize <= 0)
                {
                    slotStack = null;
                }
                return stack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if(slot == 0)
        {
            return slotStack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        if(slot == 0)
        {
            slotStack = stack;
        }
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
}
