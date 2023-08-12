package com.amedouhu.ankaplugin.listeners;

import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerChat implements Listener {
    /* PlayerChatEventの処理クラス */
    public static List<String> history = new ArrayList<>();

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        /* PlayerChatEventが発生したとき */
        // 必要な情報を収集する
        e.setCancelled(true);
        String message = e.getMessage();
        Player sender = e.getPlayer();
        if (sender.getScoreboardTags().contains("aku")) {
            sender.sendMessage(ChatColor.RED + "アク禁されているため、管理者が規制を解除するまで書き込めません...");
            return;
        }
        history.add(message);
        TextComponent text = new TextComponent();
        // コマンドを構築する
        for (int i = 0; i < message.length(); i++) {
            int b = i+2;
            if (message.length() <= b || ! message.substring(i, b).equals(">>")) {
                text.addExtra(text.getText() + message.charAt(i));
                continue;
            }
            int res;
            StringBuilder resStr = new StringBuilder();
            for (int n = b; n < message.length(); n++) {
                try {
                    sender.sendMessage(String.valueOf(message.charAt(n)));
                    Integer.parseInt(String.valueOf(message.charAt(n)));
                    resStr.append(message.charAt(n));
                }catch (Exception ex) {break;}
            }
            res = Integer.parseInt(resStr.toString());
            TextComponent command = new TextComponent();
            if (res < 1) {
                command.setText(ChatColor.RED + ">>NOT FOUND");
                command.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.RED + "404")));
            } else if (history.size() <= res) {
                command.setText(ChatColor.BLUE + ">>" + res);
                command.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.RED + "書き込み時点で該当するはありませんでした")));
            } else {
                command.setText(ChatColor.BLUE + ">>" + res);
                command.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(history.get(res-1))));
            }
            text.addExtra(command);
            i += String.valueOf(res).length() + 1;
        }
        // メッセージを送信する
        if (message.equals("fusiana")) Bukkit.broadcastMessage(history.size() + " " + ChatColor.GREEN + "192.168.0.0" + " " + ChatColor.WHITE + new SimpleDateFormat("yyyy/MM/dd(E) HH:mm:ss.SS").format(new Date()));
        else Bukkit.broadcastMessage(history.size() + " " + ChatColor.GREEN + sender.getName() + " " + ChatColor.WHITE + new SimpleDateFormat("yyyy/MM/dd(E) HH:mm:ss.SS").format(new Date()));
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(text);
        }
    }
}
