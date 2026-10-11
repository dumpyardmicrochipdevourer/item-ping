package org.xowa.itemping.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.xowa.itemping.player.PingColor;

import java.util.ArrayList;
import java.util.List;

public class PingColorCommand implements CommandExecutor, TabCompleter {

    private final PingColor color;

    public PingColorCommand(PingColor color) {
        this.color = color;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }
        if (args.length == 0) {
            return false; // usage
        }

        if (args[0].equalsIgnoreCase("random")) {
            TextColor randomColor = color.randomize(player);
            player.sendMessage(Component.text("Новый случайный цвет ping'а", randomColor));
            return true;
        }

        TextColor playerChosenColor = PingColor.parse(args[0]);
        if (playerChosenColor == null) {
            player.sendMessage(Component.text("Неизвестный цвет", NamedTextColor.RED));
            return true;
        }

        color.set(player, playerChosenColor);
        player.sendMessage(Component.text("Цвет ping'а изменён", playerChosenColor));
        return true;
    }


    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String str, String[] args) {
        if (args.length != 1) {
            return List.of();
        }

        List<String> out = new ArrayList<>(NamedTextColor.NAMES.keys());
        out.add("random");

        return out.stream().filter(x -> x.startsWith(args[0].toLowerCase())).toList();
    }
}
