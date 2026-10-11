package org.xowa.itemping.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.xowa.itemping.player.PingColor;

import java.util.Objects;

public class JoinListener implements Listener {
    private final PingColor color;

    public JoinListener(PingColor color) {
        this.color = color;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TextColor playerColor = color.get(event.getPlayer()); // randomized if null
        Component welcomeMessage = Component.text()
                .append(Component.text("[ItemPing] ", NamedTextColor.GRAY))
                .append(Component.text("Используйте команду ", NamedTextColor.YELLOW))
                .append(Component.text("/pingcolor", playerColor))
                .append(Component.text(", чтобы изменить цвет пинга предметов.", NamedTextColor.YELLOW))
                .build();
        Bukkit.getScheduler().runTaskLater(
                Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("ItemPing")),
                () -> player.sendMessage(welcomeMessage),
                20L
        );
    }
}
