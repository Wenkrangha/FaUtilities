package com.wenkrang.faClip.Module.FaWindow.event;

import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import com.wenkrang.faClip.Module.FaItem.TagMgr;
import com.wenkrang.faClip.Module.FaWindow.FaWindow;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.Module.FaWindow.helper.WinDataHelper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InvBackRefE implements Listener {
    public FaWindowInstance faWindowInstance;

    public InvBackRefE(FaWindowInstance instance) {
        faWindowInstance = instance;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();

        TagMgr tagMgr = InvRefE.getTagMgr(event, faWindowInstance);

        // 检查是否有backref标签
        if (tagMgr != null && tagMgr.has("backref")) {
            // 获取backref标签的值
            String s = tagMgr.get("backref");

            // 检查backref标签的值是否为true
            if (Boolean.parseBoolean(s)) {
                FaWindow faWin = WinDataHelper.getFaWin(inv);

                FaInventoryData data = WinDataHelper.getData(inv);

                if (data != null && data.has("backref")) {
                    Inventory backref = data.get("backref");

                    if (faWin != null) {
                        faWin.open(backref);
                    }
                }
            }
        }
    }
}
