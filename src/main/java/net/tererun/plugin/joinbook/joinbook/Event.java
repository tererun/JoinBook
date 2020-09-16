package net.tererun.plugin.joinbook.joinbook;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Event implements Listener {
    @EventHandler
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent e) {
        if (JoinBook.config.getBoolean("OnlyFirstJoin")) {
            boolean isFirst = true;
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                if (offlinePlayer.getUniqueId().equals(e.getUniqueId())) {
                    isFirst = false;
                }
            }
            if (isFirst) {
                JoinBook.uuids.add(e.getUniqueId());
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (JoinBook.config.contains("JoinBook")) {
            if (JoinBook.config.getBoolean("OnlyFirstJoin")) {
                if (JoinBook.uuids.contains(e.getPlayer().getUniqueId())) {
                    ItemStack itemStack = JoinBook.config.getItemStack("JoinBook");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            e.getPlayer().openBook(itemStack);
                        }
                    }.runTaskLater(JoinBook.plugin, JoinBook.config.getLong("OpenLaterTick"));
                    JoinBook.uuids.remove(e.getPlayer().getUniqueId());
                }
            } else {
                ItemStack itemStack = JoinBook.config.getItemStack("JoinBook");
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        e.getPlayer().openBook(itemStack);
                    }
                }.runTaskLater(JoinBook.plugin, JoinBook.config.getLong("OpenLaterTick"));
            }
        }
    }
}
