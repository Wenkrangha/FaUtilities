package com.wenkrang.faClip.module.faItem.interpreter.handler.basic;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faItem.FaItem;
import com.wenkrang.faClip.module.faItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.faItem.interpreter.handler.FaItemHandler;
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
