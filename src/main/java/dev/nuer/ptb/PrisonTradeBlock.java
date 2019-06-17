package dev.nuer.ptb;

import dev.nuer.ptb.managers.FileManager;
import dev.nuer.ptb.managers.SetupManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class PrisonTradeBlock extends JavaPlugin {
    //Store the main instance for the plugin
    public static PrisonTradeBlock instance;
    //Store the plugins logger
    public static Logger LOGGER;

    @Override
    public void onEnable() {
        instance = this;
        LOGGER = getLogger();
        //Set up the plugins files
        SetupManager.setupFiles(new FileManager(instance));
        //Register the plugins events
        SetupManager.registerEvents(instance);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
