package com.amedouhu.ankaplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Aku implements CommandExecutor {
    /* AkuCommandの処理クラス */

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        /* コマンドが実行されたとき */
        if (! sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "管理者権限で実行してください...");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "コマンド引数が不正です... /" + s + " <player>");
            return true;
        }
        String name = args[0];
        if (Bukkit.getOnlinePlayers().stream().map(Player::name).anyMatch(name::equals)) {
            sender.sendMessage(ChatColor.RED + name + "は現在オフラインです！");
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player.getScoreboardTags().contains("aku")) {
            player.removeScoreboardTag("aku");
            sender.sendMessage(name + "のアク禁を解除しました。");
        }
        else {
            player.addScoreboardTag("aku");
            sender.sendMessage(name + "のアク禁しました。");
        }
        return true;
    }
}
