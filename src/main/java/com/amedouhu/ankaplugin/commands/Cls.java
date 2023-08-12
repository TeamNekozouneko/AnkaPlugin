package com.amedouhu.ankaplugin.commands;

import com.amedouhu.ankaplugin.listeners.PlayerChat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Cls implements CommandExecutor {
    /* AnkaCommandの処理クラス */

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        /* コマンドが実行されたとき */
        if (! sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "管理者権限で実行してください...");
        }
        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "コマンド引数が不正です... /" + s);
            return true;
        }
        PlayerChat.history = new ArrayList<>();
        sender.sendMessage("書き込みを初期化しました...");
        return true;
    }
}
