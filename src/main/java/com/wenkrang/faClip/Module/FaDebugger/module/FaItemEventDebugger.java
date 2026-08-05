package com.wenkrang.faClip.Module.FaDebugger.module;

import com.wenkrang.faClip.FaClip;
import com.wenkrang.faClip.Module.FaCommand.Annotation.Cmd;
import com.wenkrang.faClip.Module.FaCommand.Annotation.Debug;
import com.wenkrang.faClip.Module.FaCommand.Annotation.ForPlayer;
import com.wenkrang.faClip.Module.FaCommand.Annotation.RequireOP;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faClip.Module.FaInterface.Annotaion.Intf;
import com.wenkrang.faClip.Module.FaInterface.FaIntfContext;
import com.wenkrang.faClip.Module.FaItem.FaItem;
import com.wenkrang.faClip.Module.FaItem.FaItemInstance;
import com.wenkrang.faClip.Module.FaMessage.Fm;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FaItemEventDebugger {
    public static FaItemInstance faItemInstance;

    @Cmd("fatest.event.load")
    @RequireOP
    @Debug
    public static void load() {
        faItemInstance = new FaItemInstance(FaClip.plugin);
        faItemInstance.loadAll();
        faItemInstance.autoRegister();
    }

    @Cmd("fatest.event.get")
    @RequireOP
    @Debug
    @ForPlayer
    public static void get(String id, FaCmdContext faCmdContext) {
        FaItem faItem = faItemInstance.getFaItem(id);

        if (faItem != null) {
            Player player = (Player) faCmdContext.sender();

            player.getInventory().addItem(faItem);
        }
    }
    @Cmd("fatest.event.getSpace")
    @RequireOP
    @Debug
    @ForPlayer
    public static void getNameSpace(FaCmdContext faCmdContext) {
        Player player = (Player) faCmdContext.sender();

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

        String id = faItemInstance.getID(itemInMainHand);

        if (id != null) {
            Fm.info(faCmdContext.sender(), id);
        }
    }

    @Intf("fatest.event.test")
    public static void test(FaIntfContext faIntfContext) {
        InventoryClickEvent event = faIntfContext.get("event");

        event.getView().getPlayer().sendMessage("测试");

        event.setCancelled(true);

    }

    @Cmd("fatest.event.open")
    @RequireOP
    @Debug
    @ForPlayer
    public static void open(FaCmdContext faCmdContext) {
        Inventory i = Bukkit.createInventory(null, 54, "test");

        i.setItem(0 ,faItemInstance.getFaItem("test"));

        Player player = (Player) faCmdContext.sender();

        player.openInventory(i);
    }
}
