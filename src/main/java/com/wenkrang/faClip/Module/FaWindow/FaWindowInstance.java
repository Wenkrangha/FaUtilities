package com.wenkrang.faClip.Module.FaWindow;

import com.wenkrang.faClip.Helper.ResourceHelper;
import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import com.wenkrang.faClip.Module.FaInterface.FaInterfaceInstance;
import com.wenkrang.faClip.Module.FaItem.FaItemInstance;
import com.wenkrang.faClip.Module.FaWindow.event.InvCloseE;
import com.wenkrang.faClip.Module.FaWindow.event.InvInitE;
import com.wenkrang.faClip.Module.FaWindow.event.InvLockE;
import com.wenkrang.faClip.Module.FaWindow.interpreter.FaInvInterpreter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaWindowInstance {
    private final Plugin plugin;

    private final FaItemInstance faItemInstance;

    private final FaInvInterpreter faInvInterpreter;

    private final FaInterfaceInstance faInterfaceInstance;

    private final Map<String, FaInventory> inventories = new HashMap<>();

    public FaWindowInstance(Plugin p,FaItemInstance f) {
        plugin = p;
        faItemInstance = f;
        faInvInterpreter = new FaInvInterpreter(this);
        faInterfaceInstance = new FaInterfaceInstance(plugin);

        registerEvents();
    }

    public void autoRegister() {
        faInterfaceInstance.enableForAll(plugin);
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public FaItemInstance getFaItemInstance() {
        return faItemInstance;
    }

    public FaInterfaceInstance getFaInterfaceInstance() {
        return faInterfaceInstance;
    }

    public void load(String path) {
        FaInventory inv = faInvInterpreter.interpreter(path);

        inventories.put(inv.id, inv);
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new InvLockE(this), plugin);
        Bukkit.getPluginManager().registerEvents(new InvInitE(this), plugin);
        Bukkit.getPluginManager().registerEvents(new InvCloseE(this), plugin);
    }

    public void loadAll() {
        ResourceHelper resourceHelper = new ResourceHelper(plugin.getClass());

        List<String> inv = resourceHelper.getResourcesByExtension("inv");

        for (String s : inv) {
            load(s);
        }
    }

    public FaWindow getWin(String s) {
        FaWindow faWindow = new FaWindow();

        FaInventory faInventory = inventories.get(s);

        faWindow.setEntryInv(faInventory);

        return faWindow;
    }

    public FaInvInterpreter getFaInvInterpreter() {
        return faInvInterpreter;
    }

    /**
     * 获取物品栏ID（如果是FaInventory的话）
     * 如果不是FaInventory则返回null
     * @param inventory 物品栏
     * @return 物品栏ID
     */
    public @Nullable String getID(Inventory inventory) {
        String result = null;

        InventoryHolder holder = inventory.getHolder();
        if (holder != null) {
            if (holder instanceof FaInventoryData faInventoryData) {
                if (faInventoryData.has("id")) {
                    result = faInventoryData.get("id");
                }
            }
        }

        return result;
    }

    /**
     * 判断物品栏是否是FaInventory
     * @param inventory 物品栏
     * @return 是否是FaInventory
     */
    public boolean isFaInventory(Inventory inventory) {
        return getID(inventory) != null;
    }

    public @Nullable List<Integer> getTag(Inventory inv,String Tag) {
        List<Integer> result = null;

        // 判断物品栏是否是FaInventory
        if (isFaInventory(inv)) {
            // 获取物品栏数据
            FaInventoryData data = (FaInventoryData) inv.getHolder();

            // 判断数据是否存在且包含指定标签
            if (data != null && data.has(Tag)) {
                result = data.get(Tag);
            }
        }

        return result;
    }
}
