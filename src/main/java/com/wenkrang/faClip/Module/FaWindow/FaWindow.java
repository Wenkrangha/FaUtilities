package com.wenkrang.faClip.Module.FaWindow;

import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import com.wenkrang.faClip.Module.FaWindow.helper.WinDataHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.wenkrang.faClip.Module.FaWindow.helper.WinDataHelper.getDesignArray;

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



    public void setBackRef(Inventory inventory) {
        FaInventoryData data = WinDataHelper.getData(inventory);

        if (data != null && !data.has("backref")) {
            data.set("backref", current);
        }
    }

    public FaInventoryData open(FaInventory faInventory) {
        // 渲染界面
        Inventory render = faWindowInstance.render(faInventory);

        // 设置数据
        FaInventoryData data = WinDataHelper.getData(render);
        assert data != null;
        faWindowInstance.setData(faInventory, data, this);

        // 打开物品栏
        viewer.openInventory(render);

        // 设置回指
        setBackRef(render);

        current = render;

        return data;
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
