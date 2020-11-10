package net.vectromc.vnitrogen.listeners;

import net.vectromc.vnitrogen.management.PlayerManagement;
import net.vectromc.vnitrogen.vNitrogen;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BanJoinListener implements Listener {

    private vNitrogen plugin;

    public BanJoinListener() {
        plugin = vNitrogen.getPlugin(vNitrogen.class);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (plugin.banned.contains(player.getUniqueId().toString())) {
            PlayerManagement playerManagement = new PlayerManagement(player);
            String reason = plugin.data.config.getString(player.getUniqueId().toString() + ".Bans." + playerManagement.getBansAmount() + ".Reason");
            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Ban.BanMessage").replaceAll("%reason%", reason).replaceAll("%executor%", player.getDisplayName())));
        }
    }
}
