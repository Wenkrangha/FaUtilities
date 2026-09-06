package com.wenkrang.faClip.module.faItem.event;

import com.wenkrang.faClip.module.faItem.FaItemInstance;
import com.wenkrang.faClip.module.faItem.interpreter.helper.ItemDataHelper;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class FaItemIsolateE implements Listener {
    private final FaItemInstance faItemInstance;

    public FaItemIsolateE(FaItemInstance faItemInstance) {
        this.faItemInstance = faItemInstance;
    }

    /**
     * 判断是否为工作台
     * @param inventory 界面
     * @return 是否为工作台
     */
    public boolean isWorkbench(Inventory inventory) {
        InventoryType type = inventory.getType();

        // 检查界面类型
        if (type == InventoryType.PLAYER
        || type == InventoryType.CHEST
        || type == InventoryType.ENDER_CHEST
        || type == InventoryType.HOPPER
        || type == InventoryType.BARREL
        || type == InventoryType.SHULKER_BOX
        || type == InventoryType.DISPENSER
        || type == InventoryType.DROPPER) {
            InventoryHolder holder = inventory.getHolder();

            // 检查界面持有者
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

                // 判空
                if (item != null && !item.getType().equals(Material.AIR)) {
                    if (ItemDataHelper.isIsolate(item)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDragItem(InventoryDragEvent event) {
        Inventory inventory = event.getInventory();

        // 判断是否是工作方块的界面
        if (isWorkbench(inventory)) {
            Set<Integer> rawSlots = event.getRawSlots();

            // 判断拖拽是否在工作方块内
            if (rawSlots.stream().anyMatch(i -> i >= 0 && i < inventory.getSize())){
                ItemStack item = event.getCursor();

                // 判空
                if (item != null && !item.getType().equals(Material.AIR)) {
                    if (ItemDataHelper.isIsolate(item)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
