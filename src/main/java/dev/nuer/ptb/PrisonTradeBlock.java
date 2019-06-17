package dev.nuer.ptb;

import dev.nuer.ptb.cmd.PtbCmd;
import dev.nuer.ptb.managers.FileManager;
import dev.nuer.ptb.managers.SetupManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Main class for the PrisonTradeBlock plugin
 */
public final class PrisonTradeBlock extends JavaPlugin {
    //Store the main instance for the plugin
    public static PrisonTradeBlock instance;
    //Store the plugins logger
    public static Logger LOGGER;

    /**
     * Method called on plugin start up
     */
    @Override
    public void onEnable() {
        instance = this;
        LOGGER = getLogger();
        //Set up the plugins files
        SetupManager.setupFiles(new FileManager(instance));
        //Register the plugins commands
        registerCommands();
        //Register the plugins events
        SetupManager.registerEvents(instance);
    }

    /**
     * Method called on plugin shutdown
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Registers all of the plugin commands
     */
    public void registerCommands() {
        getCommand("prison-trade-block").setExecutor(new PtbCmd());
    }
}
