package org.xowa.itemping.player;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.xowa.itemping.player.util.ColorGenerator;

import java.util.Locale;

public class PingColor {
    public static final TextColor DEFAULT = NamedTextColor.WHITE;
    private final NamespacedKey key;

    public PingColor(Plugin plugin) {
        this.key = new NamespacedKey(plugin, "ping_color");
    }

    public TextColor get(Player player) {
        String str = player.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        TextColor color = str == null ? null : TextColor.fromHexString(str);
        if (color != null) {
            return color;
        }
        TextColor randomColor = ColorGenerator.generateRandomColor(null);
        set(player, randomColor);
        return randomColor;
    }


    public void set(Player player, TextColor color) {
        player.getPersistentDataContainer().set(key, PersistentDataType.STRING, color.asHexString());
    }

    public void reset(Player p) {
        p.getPersistentDataContainer().remove(key);
    }

    public static TextColor parse(String in) {
        return in.startsWith("#")
                ? TextColor.fromHexString(in)
                : NamedTextColor.NAMES.value(in.toLowerCase(Locale.ROOT));
    }


    public TextColor randomize(Player player) {
        String str = player.getPersistentDataContainer().get(key, PersistentDataType.STRING);
        TextColor currentColor = str == null ? null : TextColor.fromHexString(str);
        TextColor newColor = ColorGenerator.generateRandomColor(currentColor);
        set(player, newColor);
        return newColor;
    }
}
