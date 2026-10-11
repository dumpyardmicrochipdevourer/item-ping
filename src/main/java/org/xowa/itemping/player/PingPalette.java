package org.xowa.itemping.player;

import net.kyori.adventure.text.format.NamedTextColor;

public enum PingPalette {
    RED(NamedTextColor.RED),
    GOLD(NamedTextColor.GOLD),
    YELLOW(NamedTextColor.YELLOW),
    GREEN(NamedTextColor.GREEN),
    AQUA(NamedTextColor.AQUA),
    BLUE(NamedTextColor.BLUE),
    LIGHT_PURPLE(NamedTextColor.LIGHT_PURPLE),
    DARK_GREEN(NamedTextColor.DARK_GREEN),
    DARK_AQUA(NamedTextColor.DARK_AQUA),
    DARK_PURPLE(NamedTextColor.DARK_PURPLE);

    private final NamedTextColor textColor;

    PingPalette(NamedTextColor textColor) {
        this.textColor = textColor;
    }

    public NamedTextColor getTextColor() {
        return textColor;
    }
}
