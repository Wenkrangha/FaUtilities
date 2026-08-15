package com.wenkrang.faClip.module.FaItem;

import com.wenkrang.faClip.module.FaMessage.exception.FaItemException;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

/**
 * 这是一个管理物品标签的类
 */
public class TagMgr {
    private final PersistentDataContainer container;

    private final Plugin plugin;

    private final ItemStack itemStack;

    private final ItemMeta itemMeta;

    public TagMgr(Plugin p, ItemStack i) {
        itemMeta = i.getItemMeta();
        if (itemMeta == null) {
            throw new FaItemException("FaItem.Exception.TagMgr.ItemMetaNull", i.getType().name());
        }
        container = itemMeta.getPersistentDataContainer();
        plugin = p;
        itemStack = i;
    }

    public void save() {
        itemStack.setItemMeta(itemMeta);
    }

    public void set(String key, String value) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);

        container.set(namespacedKey, PersistentDataType.STRING, value);

        save();
    }

    public String get(String key) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);

        return container.get(namespacedKey, PersistentDataType.STRING);
    }

    public void remove(String key) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);

        container.remove(namespacedKey);

        save();
    }

    public boolean has(String key) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);

        return container.has(namespacedKey, PersistentDataType.STRING);
    }
}
