package com.wenkrang.faClip.module.FaItem.interpreter.handler.basic;

import com.wenkrang.faClip.FaClip;
import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.FaMessage.helper.I18nHelper;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class LoreHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "lore";
    }

    @Override
    public void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter) {
        List<String> lore = faData.getStringList(getNode());

        if (!lore.isEmpty()) {
            ItemMeta itemMeta = faItem.getItemMeta();
            if (itemMeta != null) {
                itemMeta.setLore(lore);
                faItem.setItemMeta(itemMeta);
            }
        }
    }
}
