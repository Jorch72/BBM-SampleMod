package com.builtbroken.sample;

import com.builtbroken.mc.lib.mod.AbstractMod;
import com.builtbroken.mc.lib.mod.AbstractProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;

/**
 * Created by robert on 8/22/2014.
 */
@Mod(modid = SampleMod.NAME, name = SampleMod.MOD_ID, version = "0.0.1", dependencies = "required-after:VoltzEngine")
public class SampleMod extends AbstractMod
{
    public static final String NAME = "Voltz Engine Sample Mod";
    public static final String MOD_ID = "VESampleMod";

    @SidedProxy(clientSide = "com.builtbroken.sample.ClientProxy", serverSide = "com.builtbroken.sample.CommonProxy")
    public static CommonProxy proxy;

    public static Block wire;

    public SampleMod()
    {
        super(MOD_ID);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt)
    {
        super.preInit(evt);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent evt)
    {
        super.init(evt);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent evt)
    {
        super.postInit(evt);
    }

    @Override
    public AbstractProxy getProxy()
    {
        return proxy;
    }
}
