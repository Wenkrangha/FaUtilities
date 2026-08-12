package com.wenkrang.faClip.Module.FaWindow.helper;

import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import com.wenkrang.faClip.Module.FaWindow.FaWindow;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class WinDataHelper {

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

    public static @Nullable List<Integer> getNote(Inventory inventory,String key) {
        FaInventoryData data = getData(inventory);

        if (data != null && data.has("note")) {
            Map<String, List<Integer>> notes = data.get("note");

            return notes.get(key);
        }

        return null;
    }

    public static @NotNull List<Integer> getSlotsByID(Inventory inventory, String ID) {
        if (isFaInventory(inventory)) {
            FaInventoryData data = getData(inventory);

            if (data != null) {
                ArrayList<String> design = data.get("design");
                char[] designArray = getDesignArray(design);

                return IntStream
                        .range(0, designArray.length)
                        .filter(i -> ID.equalsIgnoreCase(String.valueOf(designArray[i])))
                        .boxed().toList();
            }
        }
        return new ArrayList<>();
    }

    public static char[] getDesignArray(ArrayList<String> design) {
        return String.join("", design).toCharArray();
    }

    public static @NotNull List<Integer> getMoveable(Inventory inventory) {
        ArrayList<Integer> result = null;

        // 判空
        if (isFaInventory(inventory)) {
            FaInventoryData data = getData(inventory);

            if (data != null && data.has("moveable")) {
                // 获取对象
                Object o = data.get("moveable");

                // 判断基本类型
                if (o instanceof List<?> list) {
                    if (!list.isEmpty()) {
                        Object first = list.getFirst();

                        // 判断列表类型
                        if (first instanceof Integer) {
                            List<Integer> moveable = data.get("moveable");

                            return new ArrayList<>(moveable);
                        } else if (first instanceof String) {
                            // 根据ID获取槽位
                            List<String> MoveableID = data.get("moveable");

                            List<Integer> moveable = new ArrayList<>();

                            for (String s : MoveableID) {
                                List<Integer> slotsByID = getSlotsByID(inventory, s);
                                moveable.addAll(slotsByID);
                            }

                            return moveable;
                        }
                    }
                } else if (o instanceof String s) {
                    return getSlotsByID(inventory, s);
                }
            }
        }

        return new ArrayList<>();
    }
}
