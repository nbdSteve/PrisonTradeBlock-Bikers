package dev.nuer.ptb.utils;

import dev.nuer.ptb.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class UUIDutil {

    public static boolean hasUUIDApplied(NBTItem nbtItem) {
        try {
            if (nbtItem.getBoolean("ptb.uuid-applied")) return true;
        } catch (Exception e) {
            //Do nothing
        }
        return false;
    }

    public static boolean hasPlayerUUID(UUID uuid, NBTItem nbtItem) {
        try {
            if (nbtItem.getBoolean("ptb.uuid-applied")) {
                return nbtItem.getString("ptb.player-uuid").equalsIgnoreCase(uuid.toString());
            }
        } catch (Exception e) {
            //Do nothing
        }
        return true;
    }

    public static ItemStack apply(UUID uuid, NBTItem nbtItem) {
        try {
            if (nbtItem.getBoolean("ptb.uuid-applied")) return nbtItem.getItem();
            nbtItem.setBoolean("ptb.uuid-applied", true);
            nbtItem.setString("ptb.player-uuid", uuid.toString());
        } catch (Exception e) {
            //Do nothing
        }
        return nbtItem.getItem();
    }
}