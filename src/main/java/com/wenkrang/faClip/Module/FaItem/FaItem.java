package com.wenkrang.faClip.Module.FaItem;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * 这是ItemStack的扩展物品类
 */
public class FaItem extends ItemStack {
    public Plugin plugin;

    public FaItem(Plugin p,ItemStack item) {
        super(item);
        plugin = p;
    }

    public FaItem(Plugin p,Material material) {
        super(new ItemStack(material));
        plugin = p;
    }

    private NamespacedKey namespacedKey;

    public void setNamespacedKey(NamespacedKey namespacedKey) {
        this.namespacedKey = namespacedKey;
    }

    public @NotNull FaItem copy() {
        ItemStack clone = super.clone();

        FaItem faItem = new FaItem(plugin, clone);

        faItem.namespacedKey = namespacedKey;

        return faItem;
    }

    public TagMgr getTagMgr() {
        return new TagMgr(plugin,this);
    }

    public void setEvent(FaItemEvent event, String node) {
        getTagMgr().set(event.toString().toLowerCase(), node);
    }
}

