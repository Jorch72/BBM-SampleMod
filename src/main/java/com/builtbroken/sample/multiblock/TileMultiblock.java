package com.builtbroken.sample.multiblock;

import com.builtbroken.jlib.data.vector.IPos3D;
import com.builtbroken.mc.api.tile.client.IIconCallBack;
import com.builtbroken.mc.api.tile.multiblock.IMultiTile;
import com.builtbroken.mc.api.tile.multiblock.IMultiTileHost;
import com.builtbroken.mc.lib.transform.vector.Pos;
import com.builtbroken.mc.prefab.tile.Tile;
import com.builtbroken.mc.prefab.tile.multiblock.EnumMultiblock;
import com.builtbroken.mc.prefab.tile.multiblock.MultiBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dark on 8/15/2015.
 */
public class TileMultiblock extends Tile implements IMultiTileHost, IIconCallBack
{
    static final HashMap<IPos3D, String> map = new HashMap();

    static
    {
        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                for (int k = -1; k < 2; k++)
                {
                    if (i != 0 || j != 0 || k != 0)
                        map.put(new Pos(i, j, k), EnumMultiblock.TILE.getName() + "#RenderBlock=true");
                }
            }
        }
    }

    private boolean _destroyingStructure = false;

    public TileMultiblock()
    {
        super("testMultiTile", Material.rock);
        this.itemBlock = ItemBlockMulti.class;

    }

    @Override
    public void firstTick()
    {
        super.firstTick();
        MultiBlockHelper.buildMultiBlock(world(), this, true);
    }

    @Override
    public Tile newTile()
    {
        return new TileMultiblock();
    }

    @Override
    public void onMultiTileAdded(IMultiTile tileMulti)
    {
        if (tileMulti instanceof TileEntity)
        {
            if (map.containsKey(new Pos(this).sub(new Pos((TileEntity) tileMulti))))
            {
                tileMulti.setHost(this);
            }
        }
    }

    @Override
    public void onRemove(Block block, int par6)
    {
        super.onRemove(block, par6);
        breakDownStructure(true);
    }

    @Override
    public boolean onMultiTileBroken(IMultiTile tileMulti, Object source, boolean harvest)
    {
        if (!_destroyingStructure && tileMulti instanceof TileEntity)
        {
            Pos pos = new Pos((TileEntity) tileMulti).sub(new Pos(this));
            if (map.containsKey(pos))
            {
                breakDownStructure(harvest);
                return true;
            }
        }
        return false;
    }

    private void breakDownStructure(boolean b)
    {
        if (!_destroyingStructure)
        {
            _destroyingStructure = true;
            MultiBlockHelper.destroyMultiBlockStructure(this, b);
            _destroyingStructure = false;
        }
    }

    @Override
    public void onTileInvalidate(IMultiTile tileMulti)
    {

    }

    @Override
    public boolean onMultiTileActivated(IMultiTile tile, EntityPlayer player, int side, IPos3D hit)
    {
        if (isServer())
        {
            player.addChatComponentMessage(new ChatComponentText("Hello you right clicked the " + ForgeDirection.getOrientation(side) + " side  of " + tile));
        }
        return true;
    }

    @Override
    public void onMultiTileClicked(IMultiTile tile, EntityPlayer player)
    {
        if (isServer())
        {
            player.addChatComponentMessage(new ChatComponentText("Hello you left clicked " + tile));
        }
    }

    @Override
    public HashMap<IPos3D, String> getLayoutOfMultiBlock()
    {
        HashMap<IPos3D, String> newMap = new HashMap();
        Pos center = new Pos(this);

        for (Map.Entry<IPos3D, String> entry : map.entrySet())
        {
            newMap.put(center.add(entry.getKey()), entry.getValue());
        }

        return newMap;
    }

    @Override
    public IIcon getIconForSide(IBlockAccess world, int x, int y, int z, int side)
    {
        switch (ForgeDirection.getOrientation(side))
        {
            case NORTH:
                return Blocks.stone.blockIcon;
            case SOUTH:
                return Blocks.gold_block.blockIcon;
            case EAST:
                return Blocks.iron_block.blockIcon;
            case WEST:
                return Blocks.diamond_block.blockIcon;
            case UP:
                return Blocks.grass.blockIcon;
            case DOWN:
                return Blocks.bedrock.blockIcon;
        }
        return Blocks.iron_block.blockIcon;
    }

    @Override
    public String toString()
    {
        return "TileMultiblock[Dim@" + (world() != null && world().provider != null ? world().provider.dimensionId : "null") + " " + xCoord + "x " + yCoord + "y " + zCoord + "z ]";
    }
}
