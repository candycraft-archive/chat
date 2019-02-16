package de.pauhull.chat;

import de.pauhull.chat.listener.AsyncPlayerChatListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Paul
 * on 26.11.2018
 *
 * @author pauhull
 */
public class Chat extends JavaPlugin {

    @Override
    public void onEnable() {
        new AsyncPlayerChatListener(this);
    }
}
