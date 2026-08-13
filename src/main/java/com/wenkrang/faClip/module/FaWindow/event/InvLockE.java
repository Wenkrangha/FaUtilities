package com.wenkrang.faClip.module.FaWindow.event;

import com.wenkrang.faClip.module.FaData.FaInventoryData;
import com.wenkrang.faClip.module.FaItem.interpreter.helper.ItemDataHelper;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.helper.WinDataHelper;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InvLockE implements Listener {
    public FaWindowInstance instance;

    public InvLockE(FaWindowInstance instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onInv(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (WinDataHelper.isFaInventory(inventory)) {
            // 获取物品栏数据
            FaInventoryData faInventoryData = (FaInventoryData) inventory.getHolder();

            int rawSlot = event.getRawSlot();

            if (rawSlot >= 0 && rawSlot < inventory.getSize()) {
                ItemStack item = inventory.getItem(rawSlot);

                // 检查物品是否可移动
                if (item != null) {
                    if (ItemDataHelper.isMoveable(item)) {
                        return;
                    }

                    // 检查鼠标物品是否可动
                    if (item.getType() == Material.AIR) {
                        if (event.getCursor() != null && ItemDataHelper.isMoveable(event.getCursor())) {
                            return;
                        }
                    }
                }

                if (faInventoryData != null) {
                    // 检查格子是否为可动格
                    if (faInventoryData.has("moveable")) {
                        List<Integer> moveable = WinDataHelper.getMoveable(inventory);

                        if (moveable.contains(rawSlot)) {
                            return;
                        }
                    }

                    // 检查物品栏是否锁定
                    if (faInventoryData.has("lock")) {
                        if (faInventoryData.get("lock")) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
