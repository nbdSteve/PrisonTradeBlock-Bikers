package dev.nuer.ptb.managers;

import dev.nuer.ptb.blocker.ApplyUUIDToItemEventHandler;
import dev.nuer.ptb.blocker.CommandInventoryBlocker;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * Class that handles setting up the plugin on start
 */
public class SetupManager {

    /**
     * Loads the files into the file manager
     *
     * @param fileManager FileManager, the plugins file manager
     */
    public static void setupFiles(FileManager fileManager) {
        fileManager.add("config", "prison-trade-block.yml");
        fileManager.add("messages", "messages.yml");
    }

    /**
     * Register all of the events for the plugin
     *
     * @param instance Plugin, the main plugin instance
     */
    public static void registerEvents(Plugin instance) {
        PluginManager pm = instance.getServer().getPluginManager();
        pm.registerEvents(new ApplyUUIDToItemEventHandler(), instance);
        pm.registerEvents(new CommandInventoryBlocker(), instance);
    }
}
