package dev.nuer.ptb.blocker;

import dev.nuer.ptb.managers.FileManager;
import dev.nuer.ptb.nbtapi.NBTItem;
import dev.nuer.ptb.utils.UUIDutil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class ApplyUUIDToItem implements Listener {

    @EventHandler
    public void playerClick(InventoryClickEvent event) {
        //Check if the event is cancelled
        if (event.isCancelled()) return;
        //Check that is was a player who clicked
        if (!(event.getWhoClicked() instanceof Player)) return;
        //Check that the item is one of the blocked types
        for (String item_type : FileManager.get("config").getStringList("untradeable-item-types")) {
            if (!event.getCurrentItem().getType().toString().equalsIgnoreCase(item_type)) return;
        }
        //Store the players uuid
        UUID uuid = event.getWhoClicked().getUniqueId();
        //Store the nbtitem
        NBTItem nbtItem = new NBTItem(event.getCurrentItem());
        if (UUIDutil.hasUUIDApplied(nbtItem)) {
            if (!UUIDutil.hasPlayerUUID(uuid, nbtItem)) {
                event.setCancelled(true);
                return;
            }
            return;
        }
        event.getClickedInventory().setItem(event.getSlot(), UUIDutil.apply(uuid, nbtItem));
    }
}