package com.builtbroken.sample.machines;

import com.builtbroken.mc.prefab.tile.TileModuleMachine;
import net.minecraft.block.material.Material;

/**
 * Created by robert on 8/24/2014.
 */
public class TileLight extends TileModuleMachine
{
    public boolean isPowered = false;

    public TileLight()
    {
        super("light", Material.rock);
        //setMaxReceive(2);
        //setMaxExtract(1);
        //setCapacity(10);
    }

    @Override
    public void update()
    {
        super.update();
        isPowered = false;
        /**
         if(getEnergyStorage().checkExtract())
         {
         getEnergyStorage().extractEnergy();
         isPowered = true;
         }
         */
    }
}
