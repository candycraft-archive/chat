package de.pauhull.chat.listener;

import de.pauhull.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 * Created by Paul
 * on 26.11.2018
 *
 * @author pauhull
 */
public class AsyncPlayerChatListener implements Listener {

    private Chat chat;

    public AsyncPlayerChatListener(Chat chat) {
        this.chat = chat;

        Bukkit.getPluginManager().registerEvents(this, chat);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        String message = event.getMessage();
        String displayName;
        if (player.getDisplayName().equals(player.getName())) {
            PermissionGroup group = getHighestPermissionGroup(player.getName());
            displayName = ChatColor.translateAlternateColorCodes('&', group.getPrefix() + player.getName() + group.getSuffix());
        } else {
            displayName = "§a" + player.getName();
        }

        message = message.replace("%", "%%");
        message = message.replace("<3", "❤");

        if (player.hasPermission("chat.color")) {
            message = ChatColor.translateAlternateColorCodes('&', message);
        }

        event.setFormat(displayName + "§8: §f" + message);
    }

    private PermissionGroup getHighestPermissionGroup(String player) {

        PermissionGroup highestGroup = null;

        for (PermissionGroup group : PermissionsEx.getUser(player).getGroups()) {
            if (highestGroup == null || group.getRank() < highestGroup.getRank()) {
                highestGroup = group;
            }
        }

        return highestGroup;
    }

}
