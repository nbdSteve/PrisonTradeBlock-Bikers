package dev.nuer.ptb.utils;

import dev.nuer.ptb.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Class that handles checking and applying a players UUID to the items NBT data.
 */
public class UUIDutil {

    /**
     * Checks if the respective item has someones UUID applied to it
     *
     * @param nbtItem NBTItem, the item to check
     * @return boolean
     */
    public static boolean hasUUIDApplied(NBTItem nbtItem) {
        try {
            if (nbtItem.getBoolean("ptb.uuid-applied")) return true;
        } catch (Exception e) {
            //Do nothing, we know that this is false
        }
        return false;
    }

    /**
     * Checks if the respective item has the specified UUID applied to it already
     *
     * @param uuid    UUID, the uuid to check
     * @param nbtItem NBTItem, the item to check
     * @return boolean
     */
    public static boolean hasPlayerUUID(UUID uuid, NBTItem nbtItem) {
        try {
            if (nbtItem.getBoolean("ptb.uuid-applied")) {
                return nbtItem.getString("ptb.player-uuid").equalsIgnoreCase(uuid.toString());
            }
        } catch (Exception e) {
            //Do nothing, we know that this is false
        }
        return false;
    }

    /**
     * Applies the players UUID to the respective item and returns the new item stack
     *
     * @param uuid    UUID, the uuid to apply
     * @param nbtItem NBTItem, the item being affected
     * @return ItemStack
     */
    public static ItemStack apply(UUID uuid, NBTItem nbtItem) {
        try {
            nbtItem.setBoolean("ptb.uuid-applied", true);
            nbtItem.setString("ptb.player-uuid", uuid.toString());
        } catch (Exception e) {
            //Do nothing
        }
        return nbtItem.getItem();
    }
}