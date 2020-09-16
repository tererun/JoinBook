package net.tererun.plugin.joinbook.joinbook;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("joinbook")) {
            if (sender.hasPermission("joinbook")) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.GOLD + JoinBook.plugin.getName() + ChatColor.WHITE + " - " + ChatColor.AQUA + "[" + JoinBook.plugin.getDescription().getVersion() + "]" + ChatColor.AQUA + " Made by @tererun1");
                    sender.sendMessage(ChatColor.GREEN + "Command" + ChatColor.WHITE + ":");
                    sender.sendMessage(ChatColor.GREEN + " /joinbook" + ChatColor.WHITE + ": " + JoinBook.config.get("LangMessage." + JoinBook.language + ".Command.Help"));
                    sender.sendMessage(ChatColor.GREEN + " /joinbook setbook" + ChatColor.WHITE + ": " + JoinBook.config.get("LangMessage." + JoinBook.language + ".Command.SetBook"));
                    sender.sendMessage(ChatColor.GREEN + " /joinbook open" + ChatColor.WHITE + ": " + JoinBook.config.get("LangMessage." + JoinBook.language + ".Command.OpenBook"));
                    return true;
                } else {
                    if (args[0].equalsIgnoreCase("setbook")) {
                        if (args.length == 1) {
                            if (player.getInventory().getItemInMainHand().getType().equals(Material.WRITTEN_BOOK)) {
                                ItemStack itemStack = player.getInventory().getItemInMainHand();
                                if (JoinBook.config.contains("JoinBook")) {
                                    JoinBook.config.set("JoinBook", null);
                                }
                                JoinBook.config.set("JoinBook", itemStack);
                                JoinBook.plugin.saveConfig();
                                sender.sendMessage(JoinBook.successCMD + JoinBook.config.get("LangMessage." + JoinBook.language + ".SetBookMessage"));
                                return true;
                            } else {
                                sender.sendMessage(JoinBook.errorCMD + JoinBook.config.get("LangMessage." + JoinBook.language + ".Have_not_book"));
                                return true;
                            }
                        } else {
                            sender.sendMessage(JoinBook.errorCMD + "");
                            return true;
                        }
                    } else if (args[0].equalsIgnoreCase("open")) {
                        if (args.length == 1) {
                            if (JoinBook.config.contains("JoinBook")) {
                                ItemStack itemStack = JoinBook.config.getItemStack("JoinBook");
                                player.openBook(itemStack);
                                sender.sendMessage(JoinBook.logCMD + JoinBook.config.get("LangMessage." + JoinBook.language + ".OpenedMessage"));
                                return true;
                            } else {
                                sender.sendMessage(JoinBook.logCMD + JoinBook.config.get("LangMessage." + JoinBook.language + ".Command.NotSetBook"));
                                return true;
                            }
                        } else {
                            sender.sendMessage(JoinBook.errorCMD + JoinBook.config.get("LangMessage." + JoinBook.language + ".Command.Error"));
                            return true;
                        }
                    }
                    return true;
                }
            } else {
                sender.sendMessage(JoinBook.errorCMD + JoinBook.config.get("LangMessage." + JoinBook.language + ".Command.Have_not_permission"));
                return true;
            }
        }
        return false;
    }
}
