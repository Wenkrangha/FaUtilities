package com.wenkrang.faClip.Module.FaWindow.helper;

import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import com.wenkrang.faClip.Module.FaWindow.FaWindow;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WinDataGetter {

    /**
     * 获取物品栏ID（如果是FaInventory的话）
     * 如果不是FaInventory则返回null
     * @param inventory 物品栏
     * @return 物品栏ID
     */
    public static @Nullable String getID(Inventory inventory) {
        String result = null;

        FaInventoryData faInventoryData = getData(inventory);

        if (faInventoryData != null) {
            if (faInventoryData.has("id")) {
                result = faInventoryData.get("id");
            }

        }

        return result;
    }

    /**
     * 判断物品栏是否是FaInventory
     * @param inventory 物品栏
     * @return 是否是FaInventory
     */
    public static boolean isFaInventory(Inventory inventory) {
        return getID(inventory) != null;
    }

    public static @Nullable List<Integer> getTag(Inventory inv, String Tag) {
        List<Integer> result = null;

        FaInventoryData data = getData(inv);

        // 判断数据是否存在且包含指定标签
        if (data != null && data.has(Tag)) {
            result = data.get(Tag);
        }

        return result;
    }

    public static @Nullable FaWindow getFaWin(Inventory inventory) {
        FaInventoryData faInventoryData = getData(inventory);
        if (faInventoryData != null) {
            if (faInventoryData.has("win")) {
                return faInventoryData.get("win");
            }
        }
        return null;
    }

    public static @Nullable FaInventoryData getData(Inventory inventory) {
        InventoryHolder holder = inventory.getHolder();
        if (holder != null) {
            // 获取数据
            if (holder instanceof  FaInventoryData data) return data;
        }
        return null;
    }
}
