package com.wenkrang.faClip.module.FaDebugger.module;

import com.wenkrang.faClip.FaClip;
import com.wenkrang.faClip.module.FaCommand.annotation.Cmd;
import com.wenkrang.faClip.module.FaCommand.annotation.Debug;
import com.wenkrang.faClip.module.FaCommand.annotation.ForPlayer;
import com.wenkrang.faClip.module.FaCommand.annotation.RequireOP;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import com.wenkrang.faClip.module.FaInterface.annotation.Intf;
import com.wenkrang.faClip.module.FaInterface.FaIntfContext;
import com.wenkrang.faClip.module.FaItem.FaItemInstance;
import com.wenkrang.faClip.module.FaItem.interpreter.helper.ItemDataHelper;
import com.wenkrang.faClip.module.FaMessage.Fm;
import com.wenkrang.faClip.module.FaWindow.FaInventory;
import com.wenkrang.faClip.module.FaWindow.FaWindow;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.List;

public class FaInvDebugger {
    public static FaWindowInstance faWindowInstance = null;
    public static FaItemInstance faItemInstance = null;

    @Cmd("fatest.inv.load")
    @RequireOP
    @ForPlayer
    @Debug
    public static void load(FaCmdContext faCmdContext) {
        ItemDataHelper.init(FaClip.plugin);
        faItemInstance = new FaItemInstance(FaClip.plugin);
        faItemInstance.loadAll();
        faItemInstance.autoRegister();

        faWindowInstance = new FaWindowInstance(FaClip.plugin, faItemInstance);
        faWindowInstance.loadAll();
        faWindowInstance.autoRegister();

        Fm.debug(faCmdContext.sender(), "FaWin调试套件已加载");
    }

    @Cmd("fatest.inv.open")
    @RequireOP
    @ForPlayer
    @Debug
    public static void open(FaCmdContext faCmdContext, String id) {
        Player player = (Player) faCmdContext.sender();

        FaInventory faInventory = faWindowInstance.getFaInventory(id);

        if (faInventory != null) {
            List<Integer> button = faInventory.getNote("button");

            button.forEach(i -> faInventory.set(i, faItemInstance.getFaItem("button")));

            FaWindow faWindow = new FaWindow(faWindowInstance, player);

            faWindow.open(faInventory);
        }
    }

    @Intf("fatest.inv.init")
    public static void onInit(FaIntfContext faIntfContext) {
        InventoryOpenEvent event = faIntfContext.get("event");

        Player player = (Player) event.getPlayer();

        Fm.info(player, "test");
    }
}
