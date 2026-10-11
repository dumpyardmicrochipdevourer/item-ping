package org.xowa.itemping;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.xowa.itemping.command.PingColorCommand;
import org.xowa.itemping.listeners.JoinListener;
import org.xowa.itemping.listeners.PingListener;
import org.xowa.itemping.player.PingColor;

public class ItemPing extends JavaPlugin {

    private PingColor pingColor;

    @Override
    public void onEnable() {
        getLogger().info("ItemPing has started!");
        this.pingColor = new PingColor(this);
        getServer().getPluginManager().registerEvents(new PingListener(pingColor), this);
        getServer().getPluginManager().registerEvents(new JoinListener(pingColor), this);

        PluginCommand command = getCommand("pingcolor");

        if (command != null) {
            PingColorCommand cmdExecutor = new PingColorCommand(this.pingColor);
            command.setExecutor(cmdExecutor); // onCommand
            command.setTabCompleter(cmdExecutor);  // onTabComplete
        } else {
            getLogger().severe("Не удалось найти команду 'pingcolor' в plugin.yml");
        }
    }

    public PingColor getPingColor() {
        return this.pingColor;
    }
}
