package com.wenkrang.faClip.Module.FaWindow;

import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类是inv文件解释后的对象，在需调用时渲染一个新Inventory返回
 */
public class FaInventory {
    public String id;

    public int size;

    public String name;

    public Map<String,String> events = new HashMap<>();
    public Map<String, ItemStack> define = new HashMap<>();
    public ArrayList<String> design = new ArrayList<>();
    public Map<String, List<Integer>> tag = new HashMap<>();

    public boolean lock = true;

    public void setData(FaInventoryData data) {
        data.set("lock", lock);
        data.set("id", id);
        data.set("name", name);

        // 应用事件
        for (Map.Entry<String, String> entry : events.entrySet()) {
            data.set("event." + entry.getKey(), entry.getValue());
        }

        // 应用标签
        for (Map.Entry<String, List<Integer>> entry : tag.entrySet()) {
            data.set("tag." + entry.getKey(), entry.getValue());
        }
    }

    /**
     * 渲染界面
     * @return 返回
     */
    public Inventory render() {
        FaInventoryData faInventoryData = new FaInventoryData();
        Inventory inventory = Bukkit.createInventory(faInventoryData, size, name);
        faInventoryData.setInventory(inventory);

        // 拼接设计流
        StringBuilder designBuilder = new StringBuilder();

        for (String d : design) {
            designBuilder.append(d);
        }

        String design = designBuilder.toString();

        char[] charArray = design.toCharArray();

        // 应用定义流
        for (int i = 0;i < charArray.length;i++) {
            char c = charArray[i];

            ItemStack itemStack = define.get(String.valueOf(c));

            inventory.setItem(i, itemStack);
        }

        // 设置数据
        setData(faInventoryData);

        return inventory;
    }
}
