package com.wenkrang.faClip.module.faWindow.event;

import com.wenkrang.faClip.module.faWindow.FaWindowInstance;
import com.wenkrang.faClip.module.faWindow.interpreter.handler.extra.InvBasicEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class InvInitE implements Listener {
    public FaWindowInstance faWindowInstance;

    public InvInitE(FaWindowInstance instance) {
        faWindowInstance = instance;
    }

    @EventHandler
    public void onInvInit(InventoryOpenEvent event) {
        Inventory inv = event.getInventory();

        InvBasicEventHandler.invoke(event, inv, "init", faWindowInstance);
    }
}
