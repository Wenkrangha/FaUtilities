package com.wenkrang.faClip.Module.FaWindow.event;

import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import com.wenkrang.faClip.Module.FaItem.TagMgr;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InvLockE implements Listener {
    public FaWindowInstance instance;

    public InvLockE(FaWindowInstance instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onInv(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (instance.isFaInventory(inventory)) {
            // 获取物品栏数据
            FaInventoryData faInventoryData = (FaInventoryData) inventory.getHolder();

            int rawSlot = event.getRawSlot();

            if (rawSlot >= 0 && rawSlot < inventory.getSize()) {
                ItemStack item = inventory.getItem(rawSlot);
                if (item != null) {
                    TagMgr tag = new TagMgr(instance.getPlugin(), item);

                    if (tag.has("moveable")) {
                        return;
                    }
                }
            }

            // 检查物品栏是否锁定
            if (faInventoryData != null && faInventoryData.has("lock")) {
                if (faInventoryData.get("lock")) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
