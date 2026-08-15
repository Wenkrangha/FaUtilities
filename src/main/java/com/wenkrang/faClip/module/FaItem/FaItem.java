package com.wenkrang.faClip.module.FaItem;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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

    public void setGroup(List<String> groups) {
        getTagMgr().set("group", String.join(",", groups));
    }

    public List<String> getGroup() {
        return List.of(getTagMgr().get("group").split(","));
    }

    public void setIsolate(boolean bool) {
        getTagMgr().set("isolate", String.valueOf(bool));
    }

    public boolean getIsolate() {
        if (getTagMgr().has("isolate")) {
            return Boolean.parseBoolean(getTagMgr().get("isolate"));
        }
        return false;
    }

    public String getTemplate() {
        return getTagMgr().get("template");
    }

    public void setTemplate(String template) {
        getTagMgr().set("template", template);
    }
}

