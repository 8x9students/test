package baka.baka;


import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;


public class magic implements Listener {

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event)  {
        Player player = event.getPlayer();
        ItemStack items = player.getInventory().getItemInMainHand();
        if (player.getItemInHand().getType() == Material.BOOK) {
            Block target = getTargetBlock(player);
            if (target != null) {
                int amounts = items.getAmount();
                int hungry = player.getFoodLevel();
                if (amounts >= 5) {
                    if (hungry >= 10) {
                        player.sendMessage(ChatColor.BLUE + "————Ｓａｔｅｌｌｉｔｅ  ｉｎ  Ｏｒｂｉｔａｌ  Ｌａｓｅｒ-ｗｅａｐｏｎ");
                        items.setAmount(amounts - 5);
                        player.setFoodLevel(hungry - 10);
                        double i = 0;
                        double k = 256;
                        while (k<=target.getY()) {
                            ((CraftWorld) player.getWorld()).getHandle().a(EnumParticle.END_ROD, target.getX(), k, target.getZ(), 10, 0.32D, 0.32D, 0.32D, 0);
                            k--;
                        }
                        while (i < 10) {
                            target.getWorld().createExplosion(target.getX(), target.getY(), target.getZ(), 10, false, false);
                            i++;
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.DARK_RED+"空腹度が足りなぁぁぁぁぁい！！");
                    }
                }
                else {
                    player.sendMessage(ChatColor.DARK_RED+"アイテムが足りなぁぁぁぁぁい！！");
                }
            }
        }
    }
    private Block getTargetBlock(Player player) {
        BlockIterator it = new BlockIterator(player, 100);
        while ( it.hasNext() ) {
            Block block = it.next();
            if ( block.getType() != Material.AIR ) {
                return block;
            }
        }
        return null;
    }
}