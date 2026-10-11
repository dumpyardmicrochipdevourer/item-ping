package org.xowa.itemping.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.xowa.itemping.player.PingColor;

public class PingListener implements Listener {

    private final PingColor pingColor;

    public PingListener(PingColor pingColor) {
        this.pingColor = pingColor;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClick() != ClickType.SHIFT_RIGHT) return;
        if (!(e.getWhoClicked() instanceof Player player)) return;

        ItemStack item = e.getCurrentItem();
        if (item == null || item.getType().isAir()) return;

        Component displayName = item.displayName();
        TextColor itemColor = displayName.color();
        if (itemColor == null) {
            itemColor = PingColor.DEFAULT;
        }

        e.setCancelled(true);

        Component itemComponent = item.displayName()
                .hoverEvent(item.asHoverEvent())
                .color(itemColor);

        Component message = Component.text(player.getName() + " показывает: ", pingColor.get(player))
                .append(itemComponent);

        if (item.getAmount() > 1) {
            message = message.append(Component.text(" x" + item.getAmount(), NamedTextColor.GRAY));
        }

        Bukkit.broadcast(message);
    }
}
