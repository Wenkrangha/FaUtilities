package com.wenkrang.faClip.module.faData;

import com.wenkrang.faClip.helper.PluginHelper;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于存放临时数据
 */
public class FaPlayerTempData {
    public static Map<String, Map<String, String>> tempData = new HashMap<>();

    public static Plugin plugin = null;

    public static void init(Plugin p) {
        plugin = p;
    }

    /**
     * 如果插件未正常初始化，进行插件追踪
     */
    public static void init() {
        if (plugin == null) plugin = PluginHelper.detectCallingPlugin();
    }

    public static void set(Player player, String Key, String Value) {
        init();
        if (!tempData.containsKey(player.getUniqueId().toString())) {
            tempData.put(player.getUniqueId().toString(), new HashMap<>());
        }
        tempData.get(player.getUniqueId().toString()).put(Key, Value);
    }

    public static String get(Player player, String Key) {
        init();
        if (tempData.containsKey(player.getUniqueId().toString())) {
            return tempData.get(player.getUniqueId().toString()).get(Key);
        }
        return null;
    }

    public static void remove(Player player) {
        init();
        tempData.remove(player.getUniqueId().toString());
    }

    public static void remove(Player player, String Key) {
        init();
        if (tempData.containsKey(player.getUniqueId().toString())) {
            tempData.get(player.getUniqueId().toString()).remove(Key);
        }
    }

    public static boolean containsKey(Player player,String key) {
        init();
        if (tempData.containsKey(player.getUniqueId().toString())) {
            return tempData.get(player.getUniqueId().toString()).containsKey(key);
        }
        return false;
    }
}
