package com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.event;

import com.wenkrang.faClip.Module.FaInterface.FaIntf;
import com.wenkrang.faClip.Module.FaInterface.FaIntfContext;
import com.wenkrang.faClip.Module.FaItem.FaItemInstance;
import com.wenkrang.faClip.Module.FaItem.tagMgr;
import com.wenkrang.faClip.Module.FaMessage.Fm;
import com.wenkrang.faClip.Module.FaMessage.Helper.i18nHelper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FaItemClickE implements Listener {
    private final Plugin plugin;
    private final FaItemInstance faItemInstance;

    public FaItemClickE(Plugin p, FaItemInstance faItemInstance) {
        plugin = p;
        this.faItemInstance = faItemInstance;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) throws InvocationTargetException, IllegalAccessException {
        ItemStack item = event.getItem();

        if (item != null && event.getHand() == EquipmentSlot.HAND) {
            tagMgr tagMgr = new tagMgr(plugin, item);

            if (tagMgr.has("event.item_click")) {
                String node = tagMgr.get("event.item_click");

                List<FaIntf> intf = faItemInstance.getFaInterfaceInstance().getIntf(node);

                if (!intf.isEmpty()) {
                    if (intf.size() == 1) {
                        FaIntfContext faIntfContext = new FaIntfContext();
                        faIntfContext.set("event", event);
                        faIntfContext.set("item", item);

                        intf.getFirst().invoke(this, faIntfContext, new String[0]);
                    }else {
                        Fm.error(i18nHelper.t("FaItem.Exception.FaItemInterpreter.EventConflict")
                                + ": " + node);
                    }
                }
            }
        }
    }
}
