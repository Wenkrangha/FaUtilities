package com.wenkrang.faClip.Module.FaWindow.event;

import com.wenkrang.faClip.Module.FaData.FaInventoryData;
import com.wenkrang.faClip.Module.FaInterface.FaInterfaceInstance;
import com.wenkrang.faClip.Module.FaInterface.FaIntf;
import com.wenkrang.faClip.Module.FaInterface.FaIntfContext;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.extra.InvBasicEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class InvInitE implements Listener {
    public FaWindowInstance faWindowInstance;

    public InvInitE(FaWindowInstance instance) {
        faWindowInstance = instance;
    }

    @EventHandler
    public void onInvInit(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();

        InvBasicEventHandler.invoke(event, inventory, "init", faWindowInstance);
    }
}
