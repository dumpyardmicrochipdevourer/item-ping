package org.xowa.itemping;

import org.bukkit.plugin.java.JavaPlugin;

public class ItemPing extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("ItemPing has started!");
        getServer().getPluginManager().registerEvents(new PingListener(), this);
    }
}
