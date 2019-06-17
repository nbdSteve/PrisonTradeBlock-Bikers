package dev.nuer.ptb.cmd;

import dev.nuer.ptb.PrisonTradeBlock;
import dev.nuer.ptb.managers.FileManager;
import dev.nuer.ptb.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class that handles the main command for PTB
 */
public class PtbCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender.hasPermission("ptb.help")) {
                if (sender instanceof Player) {
                    MessageUtil.message("messages", "help", (Player) sender);
                } else {
                    PrisonTradeBlock.LOGGER.info("The help command can only be viewed in game.");
                }
            } else {
                if (sender instanceof Player) {
                    MessageUtil.message("messages", "no-permission", (Player) sender,
                            "{node}", "ptb.help");
                }
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("h") || args[0].equalsIgnoreCase("help")) {
                if (sender.hasPermission("ptb.help")) {
                    if (sender instanceof Player) {
                        MessageUtil.message("messages", "help", (Player) sender);
                    } else {
                        PrisonTradeBlock.LOGGER.info("The help command can only be viewed in game.");
                    }
                } else {
                    if (sender instanceof Player) {
                        MessageUtil.message("messages", "no-permission", (Player) sender,
                                "{node}", "ptb.help");
                    }
                }
            } else if (args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("ptb.admin")) {
                    FileManager.reload();
                    if (sender instanceof Player) {
                        MessageUtil.message("messages", "reload", (Player) sender);
                    } else {
                        PrisonTradeBlock.LOGGER.info("You have successfully reloaded all configuration files.");
                    }
                } else {
                    if (sender instanceof Player) {
                        MessageUtil.message("messages", "no-permission", (Player) sender,
                                "{node}", "ptb.admin");
                    }
                }
            } else {
                if (sender instanceof Player) {
                    MessageUtil.message("messages", "invalid-command", (Player) sender,
                            "{reason}", "The command you entered is invalid");
                } else {
                    PrisonTradeBlock.LOGGER.info("The command you entered is invalid.");
                }
            }
        } else {
            if (sender instanceof Player) {
                MessageUtil.message("messages", "invalid-command", (Player) sender,
                        "{reason}", "The command you entered is invalid");
            } else {
                PrisonTradeBlock.LOGGER.info("The command you entered is invalid.");
            }
        }
        return true;
    }
}
