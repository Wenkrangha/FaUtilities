package com.wenkrang.faClip.Module.FaWindow;

import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 该类是inv文件解释后的对象，在需调用时渲染一个新Inventory返回
 */
public class FaInventory {
    public String id;

    public int size;

    public String name;

    public Map<String, ItemStack> define = new HashMap<>();
    public ArrayList<String> design = new ArrayList<>();

    public boolean lock = true;

    public void setData(FaInventoryData data) {
        data.set("lock", lock);
        data.set("id", id);
        data.set("name", name);
    }

    /**
     * 渲染界面
     * @return 返回
     */
    public Inventory render() {
        FaInventoryData faInventoryData = new FaInventoryData();
        Inventory inventory = Bukkit.createInventory(faInventoryData, size, name);
        faInventoryData.setInventory(inventory);

        StringBuilder designBuilder = new StringBuilder();

        for (String d : design) {
            designBuilder.append(d);
        }

        String design = designBuilder.toString();

        for (char c : design.toCharArray()) {
            ItemStack itemStack = define.get(String.valueOf(c));

            inventory.setItem(design.indexOf(c), itemStack);
        }

        // 设置数据
        setData(faInventoryData);

        return inventory;
    }
}
