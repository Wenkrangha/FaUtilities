package com.wenkrang.faClip.module.FaWindow.event;

import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.interpreter.handler.extra.InvBasicEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InvCloseE implements Listener {
    public FaWindowInstance faWindowInstance;

    public InvCloseE(FaWindowInstance instance) {
        faWindowInstance = instance;
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();

        InvBasicEventHandler.invoke(event, inventory, "close", faWindowInstance);
    }
}
