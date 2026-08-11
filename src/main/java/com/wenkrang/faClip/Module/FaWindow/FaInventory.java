package com.wenkrang.faClip.Module.FaWindow;

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

    public Map<String, String> getEvents() {
        return events;
    }

    // 事件
    private Map<String,String> events = new HashMap<>();
    // 定义
    private Map<String, ItemStack> define = new HashMap<>();

    public ArrayList<String> getDesign() {
        return design;
    }

    // 设计
    private final ArrayList<String> design = new ArrayList<>();

    public boolean lock = true;

    /**
     * 深拷贝构造，复制模板生成完全独立的新实例
     * 其中ItemStack通过clone深拷贝，其余字段为不可变类型可直接赋值
     * @param src 被复制的源对象
     */
    public FaInventory(FaInventory src) {
        this.id = src.id;
        this.size = src.size;
        this.name = src.name;
        this.lock = src.lock;

        this.events = new HashMap<>(src.events);
        design(src.design);

        this.define = new HashMap<>();
        for (Map.Entry<String, ItemStack> entry : src.define.entrySet()) {
            this.define.put(entry.getKey(), entry.getValue() == null ? null : entry.getValue().clone());
        }
    }

    public FaInventory() {
    }

    public FaInventory clone() {
        return new FaInventory(this);
    }

    public FaInventory(int size, String title) {
        this.size = size;
        this.name = title;
    }

    public void event(String eventID, String intf) {
        this.events.put(eventID, intf);
    }

    public void define(String key, ItemStack item) {
        this.define.put(key, item);
    }

    public void design(List<String> design) {
        for (int i = 0;i < design.size();i++) {
            this.design.set(i, design.get(i));
        }
    }

    public ItemStack getDefine(String key) {
        return this.define.get(key);
    }
}
