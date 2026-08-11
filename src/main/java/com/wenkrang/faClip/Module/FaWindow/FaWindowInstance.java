package com.wenkrang.faClip.Module.FaWindow;

import com.wenkrang.faClip.Helper.ResourceHelper;
import com.wenkrang.faClip.Module.FaInterface.FaInterfaceInstance;
import com.wenkrang.faClip.Module.FaItem.FaItemInstance;
import com.wenkrang.faClip.Module.FaWindow.event.*;
import com.wenkrang.faClip.Module.FaWindow.interpreter.FaInvInterpreter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

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
        Bukkit.getPluginManager().registerEvents(new InvClickE(this), plugin);
        Bukkit.getPluginManager().registerEvents(new InvRefE(this), plugin);
        Bukkit.getPluginManager().registerEvents(new InvBackRefE(this), plugin);
    }

    public void loadAll() {
        ResourceHelper resourceHelper = new ResourceHelper(plugin.getClass());

        List<String> inv = resourceHelper.getResourcesByExtension("inv");

        for (String s : inv) {
            load(s);
        }
    }


    public FaInvInterpreter getFaInvInterpreter() {
        return faInvInterpreter;
    }

    public @Nullable FaInventory getFaInventory(String id) {
        FaInventory faInventory = inventories.get(id);

        if (faInventory != null) {
            return faInventory.clone();
        }

        return null;
    }

}
