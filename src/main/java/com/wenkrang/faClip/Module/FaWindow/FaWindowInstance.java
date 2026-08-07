package com.wenkrang.faClip.Module.FaWindow;

import com.wenkrang.faClip.Helper.ResourceHelper;
import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import com.wenkrang.faClip.Module.FaItem.FaItemInstance;
import com.wenkrang.faClip.Module.FaWindow.interpreter.FaInvInterpreter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaWindowInstance {
    private final Plugin plugin;

    private final FaItemInstance faItemInstance;

    private final FaInvInterpreter faInvInterpreter;

    private final Map<String, FaInventory> inventories = new HashMap<>();

    public FaWindowInstance(Plugin p,FaItemInstance f) {
        plugin = p;
        faItemInstance = f;
        faInvInterpreter = new FaInvInterpreter(this);
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public FaItemInstance getFaItemInstance() {
        return faItemInstance;
    }

    public void load(String path) {
        FaInventory inv = faInvInterpreter.interpreter(path);

        inventories.put(inv.id, inv);
    }

    public void loadAll() {
        ResourceHelper resourceHelper = new ResourceHelper(plugin.getClass());

        List<String> inv = resourceHelper.getResourcesByPrefix("inv");

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
}
