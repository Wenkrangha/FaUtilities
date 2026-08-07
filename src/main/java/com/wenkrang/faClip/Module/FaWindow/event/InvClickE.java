package com.wenkrang.faClip.Module.FaWindow.event;

import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.extra.InvBasicEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
