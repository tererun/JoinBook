package net.tererun.plugin.joinbook.joinbook;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.UUID;

public final class JoinBook extends JavaPlugin {

    public static FileConfiguration config;
    public static Plugin plugin;
    public static String errorCMD;
    public static String logCMD;
    public static String successCMD;
    public static String language;
    public static HashSet<UUID> uuids = new HashSet<>();

    @Override
    public void onEnable() {
        errorCMD = ChatColor.AQUA + "[JoinBook]" + ChatColor.WHITE + ": " + ChatColor.DARK_RED;
        logCMD = ChatColor.AQUA + "[JoinBook]" + ChatColor.WHITE + ": " + ChatColor.GRAY;
        successCMD = ChatColor.AQUA + "[JoinBook]" + ChatColor.WHITE + ": " + ChatColor.WHITE;
        saveDefaultConfig();
        config = getConfig();
        language = config.getString("Lang");
        plugin = this;
        getCommand("joinbook").setExecutor(new Command());
        getServer().getPluginManager().registerEvents(new Event(), this);
    }

}
