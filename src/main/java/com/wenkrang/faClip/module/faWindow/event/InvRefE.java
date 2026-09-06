package com.wenkrang.faClip.module.faWindow.event;

import com.wenkrang.faClip.module.faData.FaInventoryData;
import com.wenkrang.faClip.module.faItem.TagMgr;
import com.wenkrang.faClip.module.faWindow.FaInventory;
import com.wenkrang.faClip.module.faWindow.FaWindow;
import com.wenkrang.faClip.module.faWindow.FaWindowInstance;
import com.wenkrang.faClip.module.faWindow.helper.WinDataHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class InvRefE implements Listener {
    public FaWindowInstance faWindowInstance;

    public InvRefE(FaWindowInstance instance) {
        faWindowInstance = instance;
    }

    public static @Nullable TagMgr getTagMgr(InventoryClickEvent event,FaWindowInstance faWindowInstance) {
        Inventory inv = event.getInventory();

        // 判断点击区域
        if (event.getRawSlot() >= 0 && event.getRawSlot() < inv.getSize()) {
            ItemStack item = inv.getItem(event.getRawSlot());

            if (item != null) {
                return new TagMgr(faWindowInstance.getPlugin(), item);
            }
        }
        return null;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();

        TagMgr tagMgr = getTagMgr(event, faWindowInstance);

        if (tagMgr != null && tagMgr.has("ref")) {
            // 获取跳转目标
            String ref = tagMgr.get("ref");

            FaInventory faInventory = faWindowInstance.getFaInventory(ref);

            if (faInventory != null) {
                // 判断是否有窗口
                if (WinDataHelper.isFaInventory(inv)) {
                    FaInventoryData data = WinDataHelper.getData(inv);

                    if (data != null && data.has("win")) {
                        FaWindow win = data.get("win");

                        win.open(faInventory);
                    }
                } else {
                    // 新开窗口
                    FaWindow win = new FaWindow(faWindowInstance, (Player) event.getWhoClicked());

                    win.open(faInventory);
                }
            }
        }
    }
}
