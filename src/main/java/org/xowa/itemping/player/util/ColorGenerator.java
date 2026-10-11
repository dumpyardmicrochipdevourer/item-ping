package org.xowa.itemping.player.util;

import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xowa.itemping.player.PingPalette;

import java.util.concurrent.ThreadLocalRandom;

public class ColorGenerator {

    private static final PingPalette[] VALUES = PingPalette.values();


    public static @NotNull TextColor generateRandomColor() {
        return generateRandomColor(null);
    }

    public static @NotNull TextColor generateRandomColor(@Nullable TextColor exclude) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        if (exclude == null) {
            return VALUES[rand.nextInt(VALUES.length)].getTextColor();
        }

        TextColor selected;
        do {
            selected = VALUES[rand.nextInt(VALUES.length)].getTextColor();
        } while (selected.value() == exclude.value());

        return selected;
    }
}