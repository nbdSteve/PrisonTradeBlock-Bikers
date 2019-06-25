package dev.nuer.ptb.blocker;

import dev.nuer.ptb.managers.FileManager;
import dev.nuer.ptb.utils.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Pretty simple class that blocks commands while the player has untradeable items in their inventory
 */
public class CommandInventoryBlocker implements Listener {

    @EventHandler
    public void commandPreProcess(PlayerCommandPreprocessEvent event) {
        //Check to the see if the command should be blocked
        boolean runBlocker = false;
        for (String blockedCmd : FileManager.get("config").getStringList("blocked-commands")) {
            if (event.getMessage().toLowerCase().contains(blockedCmd)) {
                runBlocker = true;
                break;
            }
        }
        if (!runBlocker) return;
        //See if the player has untradeable items in their inventory
        for (ItemStack item : event.getPlayer().getInventory()) {
            for (String item_type : FileManager.get("config").getStringList("untradeable-item-types")) {
                if (item.getType().toString().toLowerCase().equalsIgnoreCase(item_type)) {
                    event.setCancelled(true);
                    MessageUtil.message("messages", "action-cancelled", event.getPlayer(),
                            "{reason}", "That command is blocked while you have untradeable items in your inventory");
                    return;
                }
            }
        }
    }
}