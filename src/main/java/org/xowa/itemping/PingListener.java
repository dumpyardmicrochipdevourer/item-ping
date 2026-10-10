package org.xowa.itemping;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PingListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClick() != ClickType.SHIFT_RIGHT) return;
        if (!(e.getWhoClicked() instanceof Player player)) return;

        ItemStack item = e.getCurrentItem();
        if (item == null || item.getType().isAir()) return;

        e.setCancelled(true);

        Component itemComponent = item.displayName()
                .hoverEvent(item.asHoverEvent())
                .color(NamedTextColor.AQUA);

        Component message = Component.text(player.getName() + " показывает: ", NamedTextColor.AQUA)
                .append(itemComponent);

        if (item.getAmount() > 1) {
            message = message.append(Component.text(" x" + item.getAmount(), NamedTextColor.GRAY));
        }

        Bukkit.broadcast(message);
    }
}
