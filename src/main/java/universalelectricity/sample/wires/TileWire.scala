package universalelectricity.sample.wires

import net.minecraft.block.material.Material
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.{Items, Blocks}
import net.minecraft.util.{ChatComponentText, IIcon}
import net.minecraftforge.common.util.ForgeDirection
import resonant.api.electric.EnergyStorage
import resonant.api.grid.{INode, INodeProvider}
import resonant.content.prefab.java.TileNode
import resonant.lib.grid.electric.NodeElectric
import resonant.lib.prefab.TEnergyBuffer
import net.minecraft.block.Block
import resonant.lib.transform.vector.Vector3


/**
 * Created by robert on 8/22/2014.
 */
class TileWire extends TileNode(Material.cloth) with TEnergyBuffer with INodeProvider
{
  var node : NodeElectric = null

  //Constructor
  blockHardness = 1
  stepSound = Block.soundTypeCloth

  override def use(player: EntityPlayer, side: Int, vec: Vector3) : Boolean =
  {
    if(player.getCurrentEquippedItem != null && player.getCurrentEquippedItem.getItem == Items.blaze_rod)
    {
      if(server)
      {
        player.addChatComponentMessage(new ChatComponentText("TileWire: " + this +"  Node: " + getNode()));
        player.addChatComponentMessage(new ChatComponentText("V: " + getNode().getVolts() +"  A: " + getNode().getAmps()));
        player.addChatComponentMessage(new ChatComponentText("Br: " + getNode().getBranch));
      }
      return true;
    }
    return false;
  }

  override def getIcon() : IIcon =
  {
    return Blocks.wool.getIcon(0, 0)
  }

  override def getEnergyStorage(): EnergyStorage = node.getEnergyStorage()

  override def getNode[N <: INode](nodeType: Class[_ <: N], from: ForgeDirection): N =
  {
    if(nodeType.isAssignableFrom(classOf[NodeElectric]))
    {
      return getNode().asInstanceOf[N]
    }
    return null.asInstanceOf[N]
  }

  def getNode() : NodeElectric =
  {
    if(node == null)
      node = new NodeElectric(this)
    return node
  }

  override def getNodes (nodes: java.util.List[INode]) : Unit =
  {
    nodes.add(node)
  }
}