package com.wenkrang.faClip.Module.FaWindow;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class FaWindow {
    // 窗口池
    private final Map<String, Inventory> invPool = new HashMap<>();

    // 临时存储
    private final Map<String, Object> mem = new HashMap<>();

    private Player viewer;

    private Inventory current = null;

    private FaInventory EntryInv = null;

    public <T> void setMem(String id, T value) {
        mem.put(id,value);
    }

    public <T> T getMem(String id) {
        return (T) mem.get(id);
    }

    public void setEntryInv(FaInventory inv) {
        EntryInv = inv;
    }

    public Inventory startEntry() {
        Inventory render = EntryInv.render();
        invPool.put(EntryInv.id, render);

        return render;
    }

    public void pullUp(Player player) {
        current = startEntry();
        viewer = player;

        player.openInventory(current);
    }

    public FaInventory getEntryInv() {
        return EntryInv;
    }

    public Inventory getCurrent() {
        return current;
    }

    public Player getViewer() {
        return viewer;
    }
}
