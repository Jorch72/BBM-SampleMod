package com.builtbroken.sample.tests;

import com.builtbroken.mc.core.Engine;
import com.builtbroken.mc.core.proxy.NEIProxy;
import com.builtbroken.mc.prefab.tile.BlockTile;
import com.builtbroken.mc.prefab.tile.multiblock.BlockMultiblock;
import com.builtbroken.mc.prefab.tile.multiblock.EnumMultiblock;
import com.builtbroken.mc.prefab.tile.multiblock.ItemBlockMulti;
import com.builtbroken.mc.prefab.tile.multiblock.TileMulti;
import com.builtbroken.mc.testing.junit.AbstractTest;
import com.builtbroken.mc.testing.junit.ModRegistry;
import com.builtbroken.mc.testing.junit.VoltzTestRunner;
import com.builtbroken.mc.testing.junit.world.FakeWorld;
import com.builtbroken.sample.multiblock.TileMultiblock;
import junit.framework.Assert;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
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
        World world = FakeWorld.newWorld("MultiBlockTest");
        assertTrue("Area should be empty", isPlacementAir(world));
        world.setBlock(0, 10, 0, block);
        world.updateEntities();

        Assert.assertTrue("Block at 0x 10y 0z should be a MultiBlockCore", world.getBlock(0, 10, 0) == this.block);
        Assert.assertTrue("Tile at 0x 10y 0z should be an instanceof TileMultiblock", world.getTileEntity(0, 10, 0) instanceof TileMultiblock);

        TileMultiblock host = (TileMultiblock) world.getTileEntity(0, 10, 0);
        for (int y = 9; y < 12; y++)
        {
            for (int x = -1; x < 2; x++)
            {
                for (int z = -1; z < 2; z++)
                {
                    //System.out.println(x + "x " + y + "y " + z +"z");
                    Block block = world.getBlock(x, y, z);
                    TileEntity tile = world.getTileEntity(x, y, z);
                    if (y != 10 || x != 0 || z != 0)
                    {
                        Assert.assertTrue("Block at " + x + "x " + y + "y " + z + "z should be a MultiBlock", block == Engine.multiBlock);
                        Assert.assertTrue("Tile at " + x + "x " + y + "y " + z + "z should be an instanceof TileMulti", tile instanceof TileMulti);
                        Assert.assertTrue("Tile at " + x + "x " + y + "y " + z + "z host should be " + host, ((TileMulti) tile).getHost() == host);

                    }
                }
            }
        }
    }

    public void testRemovalOfPeace()
    {
        FakeWorld world = FakeWorld.newWorld("MultiBlockTest");
        //world.debugInfo = true;
        assertTrue("Area should be empty", isPlacementAir(world));
        world.setBlock(0, 10, 0, block);
        world.updateEntities();

        world.setBlockToAir(0, 10, 1);
        world.updateEntities();
        assertTrue("Area should be empty after breaking a block", isPlacementAir(world));
    }

    public boolean isPlacementAir(World world)
    {
        for (int y = 9; y < 12; y++)
        {
            for (int x = -1; x < 2; x++)
            {
                for (int z = -1; z < 2; z++)
                {
                    //System.out.println(x + "x " + y + "y " + z +"z");
                    Block block = world.getBlock(x, y, z);
                    if (block != Blocks.air)
                    {
                        System.out.println("Block at " + x + "x " + y + "y " + z + "z is not air, " + block);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void testRemovalOfCore()
    {
        World world = FakeWorld.newWorld("MultiBlockTest");
        assertTrue("Area should be empty", isPlacementAir(world));
        world.setBlock(0, 10, 0, block);
        world.updateEntities();

        world.setBlockToAir(0, 10, 0);
        world.updateEntities();
        assertTrue("Area should be empty after breaking a block", isPlacementAir(world));
    }
}
