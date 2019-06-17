package dev.nuer.ptb.blocker;

import dev.nuer.ptb.managers.FileManager;
import dev.nuer.ptb.nbtapi.NBTItem;
import dev.nuer.ptb.utils.UUIDutil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.UUID;

public class ApplyUUIDToItemEventHandler implements Listener {

    @EventHandler
    public void playerClick(InventoryClickEvent event) {
        //Check if the event is cancelled
        if (event.isCancelled()) return;
        //Check that is was a player who clicked
        if (!(event.getWhoClicked() instanceof Player)) return;
        //Check that the item is not null
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) return;
        //Check that the item is one of the blocked types
        boolean valid_item = false;
        for (String item_type : FileManager.get("config").getStringList("untradeable-item-types")) {
            if (event.getCurrentItem().getType().toString().equalsIgnoreCase(item_type)) {
                valid_item = true;
                break;
            }
        }
        if (!valid_item) return;
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
        event.setCurrentItem(UUIDutil.apply(uuid, nbtItem));
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        //Check if the event is cancelled
        if (event.isCancelled()) return;
        //Check that the item is not null
        if (event.getItem() == null || event.getItem().getType().equals(Material.AIR)) return;
        //Check that the item is one of the blocked types
        boolean valid_item = false;
        for (String item_type : FileManager.get("config").getStringList("untradeable-item-types")) {
            if (event.getItem().getType().toString().equalsIgnoreCase(item_type)) {
                valid_item = true;
                break;
            }
        }
        if (!valid_item) return;
        //Store the players uuid
        UUID uuid = event.getPlayer().getUniqueId();
        //Store the nbtitem
        NBTItem nbtItem = new NBTItem(event.getItem());
        if (UUIDutil.hasUUIDApplied(nbtItem)) {
            if (!UUIDutil.hasPlayerUUID(uuid, nbtItem)) {
                event.setCancelled(true);
                return;
            }
            return;
        }
        event.getPlayer().setItemInHand(UUIDutil.apply(uuid, nbtItem));
    }

    @EventHandler
    public void playerPickup(PlayerPickupItemEvent event) {
        //Check if the event is cancelled
        if (event.isCancelled()) return;
        //Store the players uuid
        UUID uuid = event.getPlayer().getUniqueId();
        //Store the nbtitem
        NBTItem nbtItem = new NBTItem(event.getItem().getItemStack());
        if (UUIDutil.hasUUIDApplied(nbtItem)) {
            if (!UUIDutil.hasPlayerUUID(uuid, nbtItem)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void playerDrop(PlayerDropItemEvent event) {
        //Check if the event is cancelled
        if (event.isCancelled()) return;
        //Store the players uuid
        UUID uuid = event.getPlayer().getUniqueId();
        //Store the nbtitem
        NBTItem nbtItem = new NBTItem(event.getItemDrop().getItemStack());
        if (UUIDutil.hasUUIDApplied(nbtItem)) {
            if (UUIDutil.hasPlayerUUID(uuid, nbtItem)) {
                event.setCancelled(true);
            }
        }
    }
}