package com.wenkrang.faClip.module.FaWindow.event;

import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.interpreter.handler.extra.InvBasicEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InvClickE implements Listener {
    public FaWindowInstance faWindowInstance;

    public InvClickE(FaWindowInstance instance) {
        faWindowInstance = instance;
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        InvBasicEventHandler.invoke(event, inventory, "click", faWindowInstance);
    }
}
