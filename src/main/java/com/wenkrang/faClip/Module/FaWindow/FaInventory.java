package com.wenkrang.faClip.Module.FaWindow;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类是inv文件解释后的对象，在需调用时渲染一个新Inventory返回
 */
public class FaInventory {
    private final FaWindowInstance faWindowInstance;

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

    // 设计
    private final ArrayList<String> design = new ArrayList<>();

    // 标签
    private final Map<String, List<Integer>> note = new HashMap<>();

    private Object moveableSlots;

    private final Map<Integer, ItemStack> override = new HashMap<>();

    public boolean lock = true;

    private void setMoveable(FaInventory target,FaInventory src) {
        Object moveable = src.moveableSlots;

        if (moveable instanceof List<?>) {
            target.moveableSlots = new ArrayList<>((List<?>) moveable);
        } else if (moveable instanceof String s) {
            target.moveableSlots = s;
        }
    }

    /**
     * 深拷贝构造，复制模板生成完全独立的新实例
     * 其中ItemStack通过clone深拷贝，其余字段为不可变类型可直接赋值
     * @param src 被复制的源对象
     */
    public FaInventory(FaInventory src) {
        this.faWindowInstance = src.faWindowInstance;

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

        this.note.putAll(src.note);

        setMoveable(this, src);
    }

    public FaInventory(FaWindowInstance instance) {
        faWindowInstance = instance;
    }

    public Map<String, ItemStack> getDefine() {
        return define;
    }

    public ArrayList<String> getDesign() {
        return design;
    }

    public FaWindowInstance getFaWindowInstance() {
        return faWindowInstance;
    }

    public FaInventory clone() {
        return new FaInventory(this);
    }

    public Map<Integer, ItemStack> getOverride() {
        return override;
    }

    public FaInventory(FaWindowInstance faWindowInstance, int size, String title) {
        this.faWindowInstance = faWindowInstance;
        this.size = size;
        this.name = title;
    }

    public Object getMoveableSlots() {
        return moveableSlots;
    }

    public void setMoveableSlots(Object moveableSlots) {
        this.moveableSlots = moveableSlots;
    }

    public void event(String eventID, String intf) {
        this.events.put(eventID, intf);
    }

    public void define(String key, ItemStack item) {
        this.define.put(key, item);
    }

    public void design(List<String> design) {
        this.design.clear();
        this.design.addAll(design);
    }

    public ItemStack getDefine(String key) {
        return this.define.get(key);
    }

    public void note(String key, List<Integer> slots) {
        this.note.put(key, slots);
    }

    public @NotNull List<Integer> getNote(String key) {
        return this.note.get(key) == null ? new ArrayList<>() : this.note.get(key);
    }

    public Map<String, List<Integer>> getAllNote() {
        return this.note;
    }

    public void set(int slot, ItemStack i) {
        override.put(slot, i);
    }

    public ItemStack get(int slot) {
        return faWindowInstance.render(this).getItem(slot);
    }
}
