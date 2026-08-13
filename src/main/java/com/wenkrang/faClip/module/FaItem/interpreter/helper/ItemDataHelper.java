package com.wenkrang.faClip.module.FaItem.interpreter.helper;

import com.wenkrang.faClip.helper.PluginHelper;
import com.wenkrang.faClip.module.FaItem.TagMgr;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemDataHelper {
    private static Plugin plugin = null;

    public static void init(Plugin p) {
        plugin = p;
    }

    public static void init() {
        if (plugin == null) {
            Plugin p = PluginHelper.detectCallingPlugin();
            if (p != null) {
                plugin = p;
            }else {
                throw new RuntimeException("无法找到插件调用方（FaClip内部调试禁止不经过手动初始化使用ItemDataHelper）");
            }
        }
    }

    public static boolean isMoveable(ItemStack itemStack) {
        init();

        TagMgr tagMgr = new TagMgr(plugin, itemStack);

        return tagMgr.has("moveable");
    }

    /**
     * 获取Item的FaItem ID
     * @param i 物品
     * @return 如果物品属于FaItem，将返回他的FaItem ID，如果不是就返回null
     */
    public static @Nullable String getID(ItemStack i) {
        TagMgr tagMgr = new TagMgr(plugin, i);

        if (tagMgr.has("id")) {
            return tagMgr.get("id");
        }else {
            return null;
        }
    }

    /**
     * 判断物品是否等于指定 ID
     *
     * @param itemStack 要判断的物品
     * @param id        要判断的 ID
     * @return 是否等于指定 ID
     */
    public static boolean equals(@NotNull ItemStack itemStack, String id) {
        if (itemStack.getItemMeta() == null) return false;

        TagMgr tagMgr = new TagMgr(plugin, itemStack);

        return tagMgr.has("id") && id.equals(tagMgr.get("id"));
    }

    public static @NotNull List<String> getGroup(@NotNull ItemStack itemStack) {
        ArrayList<String> result = new ArrayList<>();

        TagMgr tagMgr = new TagMgr(plugin, itemStack);

        if (tagMgr.has("group")) {
            return List.of(tagMgr.get("group").split(","));
        }
        return result;
    }

    public static boolean isIsolate(ItemStack itemStack) {
        TagMgr tagMgr = new TagMgr(plugin, itemStack);

        if (tagMgr.has("isolate")) {
            return Boolean.parseBoolean(tagMgr.get("isolate"));
        }
        return false;
    }
}
