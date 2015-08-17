package com.builtbroken.sample.multiblock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Dark on 8/16/2015.
 */
public class ItemBlockMulti extends ItemBlock
{
    public ItemBlockMulti(Block tile)
    {
        super(tile);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x_clicked, int y_clicked, int z_clicked, int side, float xHit, float yHit, float zHit)
    {
        if (stack != null && stack.stackSize > 0)
        {
            Block block = world.getBlock(x_clicked, y_clicked, z_clicked);
            if (block == Blocks.snow_layer && (world.getBlockMetadata(x_clicked, y_clicked, z_clicked) & 7) < 1)
            {
                side = 1;
            }

            ForgeDirection dir = ForgeDirection.getOrientation(side);
            if (dir != ForgeDirection.UP)
            {
                if (!world.isRemote)
                    player.addChatComponentMessage(new ChatComponentText("This multiblock can only be placed on the top side of a block"));
                return false;
            }

            //Offset placement based on what we click
            if (!(block == Blocks.air || block.isAir(world, x_clicked, y_clicked, z_clicked) || block.isReplaceable(world, x_clicked, y_clicked, z_clicked)))
            {
                x_clicked += dir.offsetX * 2;
                y_clicked += dir.offsetY * 2;
                z_clicked += dir.offsetZ * 2;
            }
            else
            {
                x_clicked += dir.offsetX;
                y_clicked += dir.offsetY;
                z_clicked += dir.offsetZ;
            }

            if (y_clicked == 255 || y_clicked + 1 == 255)
            {
                if (!world.isRemote)
                    player.addChatComponentMessage(new ChatComponentText("Placement is too high"));
                return false;
            }
            else if (!player.canPlayerEdit(x_clicked, y_clicked, z_clicked, side, stack))
            {
                if (!world.isRemote)
                    player.addChatComponentMessage(new ChatComponentText("You can't edit this block"));
                return false;
            }
            //check if we have enough room to place
            for (int y = y_clicked - 1; y < y_clicked + 1; y++)
            {
                for (int x = x_clicked - 1; x < x_clicked + 1; x++)
                {
                    for (int z = z_clicked - 1; z < z_clicked + 1; z++)
                    {
                        Block block1 = world.getBlock(x, y, z);
                        if (!(block1 == Blocks.air || block1.isAir(world, x, y, z) || block1.isReplaceable(world, x, y, z)))
                        {
                            if (!world.isRemote)
                                player.addChatComponentMessage(new ChatComponentText("Not enough space"));
                            return false;
                        }
                    }
                }
            }

            //Place core block
            int placementMeta = this.field_150939_a.onBlockPlaced(world, x_clicked, y_clicked, z_clicked, side, xHit, yHit, zHit, this.getMetadata(stack.getItemDamage()));

            if (placeBlockAt(stack, player, world, x_clicked, y_clicked, z_clicked, side, xHit, yHit, zHit, placementMeta))
            {
                world.playSoundEffect((double) ((float) x_clicked + 0.5F), (double) ((float) y_clicked + 0.5F), (double) ((float) z_clicked + 0.5F), this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);
                --stack.stackSize;
            }

            return true;
        }
        return false;
    }
}
