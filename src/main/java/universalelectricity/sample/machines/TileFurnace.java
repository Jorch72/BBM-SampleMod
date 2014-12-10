package universalelectricity.sample.machines;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import resonant.lib.prefab.tile.TileElectricInventory;

/**
 * Created by robert on 8/24/2014.
 */
public class TileFurnace extends TileElectricInventory
{
    int currentItemBurnTime;
    public TileFurnace()
    {
        super(Material.rock);
        setCapacity(10000D);
        setMaxReceive(500D);
        setMaxExtract(0D);
    }

    @Override
    public void update()
    {
        super.update();
        if(smeltStack() != null && getEnergyStorage().checkExtract())
        {
            getEnergyStorage().extractEnergy();
            currentItemBurnTime ++;
            if(currentItemBurnTime >= 200 * smeltStack().stackSize)
            {
                ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(smeltStack());
                int maxStack = result.getMaxStackSize();

                currentItemBurnTime = 0;
            }
        }else if(smeltStack() == null)
        {
            currentItemBurnTime = 0;
        }
    }

    /** @return stack in slot one */
    public ItemStack smeltStack()
    {
        return getStackInSlot(0);
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
