package com.wenkrang.faClip.module.FaItem.event;

import com.wenkrang.faClip.module.FaInterface.FaIntf;
import com.wenkrang.faClip.module.FaInterface.FaIntfContext;
import com.wenkrang.faClip.module.FaItem.FaItemInstance;
import com.wenkrang.faClip.module.FaItem.TagMgr;
import com.wenkrang.faClip.module.FaMessage.Fm;
import com.wenkrang.faClip.module.FaMessage.Helper.I18nHelper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FaItemInvClickE implements Listener {
    private final Plugin plugin;
    private final FaItemInstance faItemInstance;

    public FaItemInvClickE(Plugin p, FaItemInstance faItemInstance) {
        plugin = p;
        this.faItemInstance = faItemInstance;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) throws InvocationTargetException, IllegalAccessException {
        if (event.getRawSlot() > 0 && event.getRawSlot() < event.getInventory().getSize()) {
            ItemStack item = event.getInventory().getItem(event.getRawSlot());

            if (item != null) {
                TagMgr tagMgr = new TagMgr(plugin, item);

                if (tagMgr.has("event.inv_click")) {
                    String node = tagMgr.get("event.inv_click");

                    List<FaIntf> intf = faItemInstance.getFaInterfaceInstance().getIntf(node);

                    if (!intf.isEmpty()) {
                        if (intf.size() == 1) {
                            FaIntfContext faIntfContext = new FaIntfContext();
                            faIntfContext.set("event", event);
                            faIntfContext.set("item", item);

                            intf.getFirst().invoke(this, faIntfContext, new String[0]);
                        }else {
                            Fm.error(I18nHelper.t("FaItem.Exception.FaItemInterpreter.EventConflict")
                                    + ": " + node);
                        }
                    }
                }
            }
        }
    }
}
