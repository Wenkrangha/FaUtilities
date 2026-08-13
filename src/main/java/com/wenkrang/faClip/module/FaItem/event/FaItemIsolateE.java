package com.wenkrang.faClip.module.FaItem.event;

import com.wenkrang.faClip.module.FaItem.FaItemInstance;
import com.wenkrang.faClip.module.FaItem.interpreter.helper.ItemDataHelper;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class FaItemIsolateE implements Listener {
    private final FaItemInstance faItemInstance;

    public FaItemIsolateE(FaItemInstance faItemInstance) {
        this.faItemInstance = faItemInstance;
    }

    public boolean isWorkbench(Inventory inventory) {
        InventoryType type = inventory.getType();

        if (type == InventoryType.PLAYER
        || type == InventoryType.CHEST
        || type == InventoryType.ENDER_CHEST
        || type == InventoryType.HOPPER
        || type == InventoryType.BARREL
        || type == InventoryType.SHULKER_BOX
        || type == InventoryType.DISPENSER
        || type == InventoryType.DROPPER) {
            InventoryHolder holder = inventory.getHolder();

            if (holder != null) {
                return !(holder instanceof Player
                        || holder instanceof Chest
                        || holder instanceof ShulkerBox
                        || holder instanceof Hopper
                        || holder instanceof HopperMinecart
                        || holder instanceof Barrel
                        || holder instanceof StorageMinecart
                        || holder instanceof DoubleChest);
            }
        }

        return true;
    }

    @EventHandler
    public void onMoveItem(InventoryMoveItemEvent event) {
        Inventory destination = event.getDestination();

        // 判断类型是否为工作方块的界面
        if (isWorkbench(destination)) {
            ItemStack item = event.getItem();

            if (!item.getType().equals(Material.AIR)) {
                if (ItemDataHelper.isIsolate(item)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMoveItem(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        // 判断类型是否为工作方块的界面
        if (isWorkbench(inventory)) {
            if (event.getRawSlot() >= 0 && event.getRawSlot() < inventory.getSize()) {
                ItemStack item = event.getCursor();

                if (item != null && !item.getType().equals(Material.AIR)) {
                    if (ItemDataHelper.isIsolate(item)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
