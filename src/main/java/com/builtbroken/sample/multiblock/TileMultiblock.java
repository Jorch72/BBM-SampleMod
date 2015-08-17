package com.builtbroken.sample.multiblock;

import com.builtbroken.jlib.data.vector.IPos3D;
import com.builtbroken.mc.api.tile.multiblock.IMultiTile;
import com.builtbroken.mc.api.tile.multiblock.IMultiTileHost;
import com.builtbroken.mc.lib.transform.vector.Pos;
import com.builtbroken.mc.prefab.tile.Tile;
import com.builtbroken.mc.prefab.tile.multiblock.EnumMultiblock;
import com.builtbroken.mc.prefab.tile.multiblock.MultiBlockHelper;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.HashMap;

/**
 * Created by Dark on 8/15/2015.
 */
public class TileMultiblock extends Tile implements IMultiTileHost
{
    public TileMultiblock()
    {
        super("testMultiTile", Material.rock);
        this.itemBlock = ItemBlockMulti.class;

    }

    @Override
    public void onWorldJoin()
    {
        MultiBlockHelper.buildMultiBlock(getWorldObj(), this);
    }

    @Override
    public Tile newTile()
    {
        return new TileMultiblock();
    }

    @Override
    public void onMultiTileAdded(IMultiTile tileMulti)
    {

    }

    @Override
    public void onMultiTileBroken(IMultiTile tileMulti)
    {

    }

    @Override
    public void onTileInvalidate(IMultiTile tileMulti)
    {

    }

    @Override
    public void onMultiTileBrokenByExplosion(IMultiTile tile, Explosion ex)
    {

    }

    @Override
    public void onMultiTileActivated(IMultiTile tile, EntityPlayer player, int side, IPos3D hit)
    {
        if (isServer())
        {
            player.addChatComponentMessage(new ChatComponentText("Hello you right clicked the " + ForgeDirection.getOrientation(side) + " side  of " + tile));
        }
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
        HashMap<IPos3D, String> map = new HashMap();
        Pos center = new Pos(this);
        //center layer
        map.put(center.add(1, 0, 0), EnumMultiblock.TILE.getName());
        map.put(center.add(-1, 0, 0), EnumMultiblock.TILE.getName());
        map.put(center.add(0, 0, 1), EnumMultiblock.TILE.getName());
        map.put(center.add(0, 0, -1), EnumMultiblock.TILE.getName());
        map.put(center.add(1, 0, 1), EnumMultiblock.TILE.getName());
        map.put(center.add(-1, 0, 1), EnumMultiblock.TILE.getName());
        map.put(center.add(1, 0, -1), EnumMultiblock.TILE.getName());
        map.put(center.add(-1, 0, -1), EnumMultiblock.TILE.getName());

        //Top layer
        map.put(center.add(0, 1, 0), EnumMultiblock.TILE.getName());
        map.put(center.add(1, 1, 0), EnumMultiblock.TILE.getName());
        map.put(center.add(1, 1, 0), EnumMultiblock.TILE.getName());
        map.put(center.add(-1, 1, 0), EnumMultiblock.TILE.getName());
        map.put(center.add(0, 1, 1), EnumMultiblock.TILE.getName());
        map.put(center.add(0, 1, -1), EnumMultiblock.TILE.getName());
        map.put(center.add(1, 1, 1), EnumMultiblock.TILE.getName());
        map.put(center.add(-1, 1, 1), EnumMultiblock.TILE.getName());
        map.put(center.add(1, 1, -1), EnumMultiblock.TILE.getName());
        map.put(center.add(-1, 1, -1), EnumMultiblock.TILE.getName());

        //Bottom layer
        map.put(center.add(0, -1, 0), EnumMultiblock.TILE.getName());
        map.put(center.add(1, -1, 0), EnumMultiblock.TILE.getName());
        map.put(center.add(-1, -1, 0), EnumMultiblock.TILE.getName());
        map.put(center.add(0, -1, 1), EnumMultiblock.TILE.getName());
        map.put(center.add(0, -1, -1), EnumMultiblock.TILE.getName());
        map.put(center.add(1, -1, 1), EnumMultiblock.TILE.getName());
        map.put(center.add(-1, -1, 1), EnumMultiblock.TILE.getName());
        map.put(center.add(1, -1, -1), EnumMultiblock.TILE.getName());
        map.put(center.add(-1, -1, -1), EnumMultiblock.TILE.getName());

        return map;
    }
}
