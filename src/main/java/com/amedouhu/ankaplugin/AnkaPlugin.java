package com.amedouhu.ankaplugin;

import com.amedouhu.ankaplugin.commands.Aku;
import com.amedouhu.ankaplugin.commands.Cls;
import com.amedouhu.ankaplugin.listeners.PlayerChat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnkaPlugin extends JavaPlugin {
    /* AnkaPluginのプラグインクラス */
    private static JavaPlugin plugin;
    public static JavaPlugin getPlugin() {return plugin;}

    @Override
    public void onEnable() {
        /* プラグインの起動ロジック */
        // ロジックの定義
        super.onEnable();
        plugin = this;
        // イベントリスナーの定義
        Bukkit.getPluginManager().registerEvents(new PlayerChat(), this);
        // プラグインコマンドの定義
        getCommand("aku").setExecutor(new Aku());
        getCommand("cls").setExecutor(new Cls());
    }

    @Override
    public void onDisable() {
        /* プラグインの終了ロジック */
        // ロジックの定義
        super.onDisable();
    }
}
