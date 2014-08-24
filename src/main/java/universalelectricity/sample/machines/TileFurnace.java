package universalelectricity.sample.machines;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import universalelectricity.sample.prefab.TileMachine;
import universalelectricity.sample.prefab.TileSingleSlotMachine;

/**
 * Created by robert on 8/24/2014.
 */
public class TileFurnace extends TileSingleSlotMachine
{
    int currentItemBurnTime;
    public TileFurnace()
    {
        super();
        buffer().setCapacity(10000);
        buffer().setMaxReceive(500);
        buffer().setMaxExtract(0);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        if(slotStack != null && buffer().checkExtract())
        {
            buffer().extractEnergy();
            currentItemBurnTime ++;
            if(currentItemBurnTime >= 200 * slotStack.stackSize)
            {
                ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(slotStack);
                int maxStack = result.getMaxStackSize();

                currentItemBurnTime = 0;
            }
        }else if(slotStack == null)
        {
            currentItemBurnTime = 0;
        }
    }


    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot == 0 && FurnaceRecipes.smelting().getSmeltingResult(stack) != null;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side)
    {
        return slot == 0 && FurnaceRecipes.smelting().getSmeltingResult(stack) == null;
    }

    @Override
    public String getInventoryName() {
        return "SampleFurnace";
    }
}
