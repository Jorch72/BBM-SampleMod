package com.builtbroken.sample.tests;

import com.builtbroken.mc.core.Engine;
import com.builtbroken.mc.core.proxy.NEIProxy;
import com.builtbroken.mc.core.registry.ModManager;
import com.builtbroken.mc.prefab.tile.BlockTile;
import com.builtbroken.mc.prefab.tile.multiblock.BlockMultiblock;
import com.builtbroken.mc.prefab.tile.multiblock.EnumMultiblock;
import com.builtbroken.mc.prefab.tile.multiblock.ItemBlockMulti;
import com.builtbroken.mc.testing.junit.AbstractTest;
import com.builtbroken.mc.testing.junit.ModRegistry;
import com.builtbroken.mc.testing.junit.VoltzTestRunner;
import com.builtbroken.mc.testing.junit.world.FakeWorld;
import com.builtbroken.sample.multiblock.TileMultiblock;
import cpw.mods.fml.common.registry.GameRegistry;
import junit.framework.Assert;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import org.junit.runner.RunWith;

/**
 * Created by Cow Pi on 8/21/2015.
 */
@RunWith(VoltzTestRunner.class)
public class TestMultiBlock extends AbstractTest
{
    Block block;

    @Override
    public void setUpForEntireClass()
    {
        super.setUpForEntireClass();
        if (Engine.multiBlock == null)
        {
            Engine.multiBlock = new BlockMultiblock();
            ModRegistry.registerBlock(Engine.multiBlock, ItemBlockMulti.class, "multiBlock");
            NEIProxy.hideItem(Engine.multiBlock);
            EnumMultiblock.register();
        }
        block = new BlockTile(new TileMultiblock(), "test:", CreativeTabs.tabAllSearch);
        ModRegistry.registerBlock(block, ((BlockTile) block).staticTile.itemBlock, "tesMultiBlockStr");
    }

    public void testInit()
    {
        Assert.assertTrue("Core block shouldn't be null", block != null);
    }

    public void testPlacement()
    {
        FakeWorld world = new FakeWorld();
        world.setBlock(0, 10, 0, block);
        world.updateEntities();
        for (int y = 9; y < 12; y++)
        {
            for (int x = -1; x < 2; x++)
            {
                for (int z = -1; z < 2; z++)
                {
                    Block block = world.getBlock(x, y, z);
                    if (y != 10 || x != 0 && z != 0)
                        Assert.assertTrue("Block at " + x + "x " + y + "y " + z + "z should be a MultiBlock", block == Engine.multiBlock);
                    else
                        Assert.assertTrue("Block at " + x + "x " + y + "y " + z + "z should be a MultiBlockCore", block == this.block);

                }
            }
        }
    }

    public void testRemovalOfPeace()
    {

    }

    public void testRemovalOfCore()
    {

    }
}
