package com.wenkrang.faClip.Module.FaWindow;

import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import com.wenkrang.faClip.Module.FaWindow.helper.WinDataGetter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class FaWindow {
    public FaWindowInstance faWindowInstance;

    // 临时存储
    private final Map<String, Object> mem = new HashMap<>();

    public <T> void setMem(String id, T value) {
        mem.put(id,value);
    }

    public <T> T getMem(String id) {
        return (T) mem.get(id);
    }

    public Inventory current = null;

    // 会话玩家
    public Player viewer;

    public FaWindow(FaWindowInstance faWindowInstance, Player player) {
        this.viewer = player;
        this.faWindowInstance = faWindowInstance;
    }

    /**
     * 设置界面数据
     * @param inv 界面
     * @param data 界面数据
     */
    public void setData(FaInventory inv,FaInventoryData data) {
        data.set("lock", inv.lock);
        data.set("id", inv.id);
        data.set("name", inv.name);

        // 应用事件
        for (Map.Entry<String, String> entry : inv.getEvents().entrySet()) {
            data.set("event." + entry.getKey(), entry.getValue());
        }

        data.set("win", this);
    }

    /**
     * 渲染界面
     * @return 返回
     */
    public Inventory render(FaInventory inv) {
        // 从初始化数据
        FaInventoryData faInventoryData = new FaInventoryData();
        Inventory inventory = Bukkit.createInventory(faInventoryData, inv.size, inv.name);
        faInventoryData.setInventory(inventory);

        // 拼接设计流
        StringBuilder designBuilder = new StringBuilder();

        for (String d : inv.getDesign()) {
            designBuilder.append(d);
        }

        String design = designBuilder.toString();

        char[] charArray = design.toCharArray();

        // 应用定义流
        for (int i = 0;i < charArray.length;i++) {
            char c = charArray[i];

            ItemStack itemStack = inv.getDefine(String.valueOf(c));

            inventory.setItem(i, itemStack.clone());
        }

        // 设置数据
        setData(inv, faInventoryData);

        return inventory;
    }

    public void setBackRef(Inventory inventory) {
        FaInventoryData data = WinDataGetter.getData(inventory);

        if (data != null && !data.has("backref")) {
            data.set("backref", current);
        }
    }

    public FaInventoryData open(FaInventory faInventory) {
        // 渲染界面
        Inventory render = render(faInventory);

        viewer.openInventory(render);

        setBackRef(render);

        current = render;

        return WinDataGetter.getData(render);
    }

    public @Nullable FaInventoryData open(Inventory inventory) {
        InventoryHolder holder = inventory.getHolder();

        if (holder != null) {
            if (holder instanceof FaInventoryData faInventoryData) {
                // 回指
                faInventoryData.set("win", this);

                // 界面回指
                setBackRef(inventory);

                faInventoryData.setInventory(inventory);

                current = inventory;

                viewer.openInventory(inventory);

                return faInventoryData;
            }
        }

        current = inventory;

        viewer.openInventory(inventory);

        return null;
    }
}
