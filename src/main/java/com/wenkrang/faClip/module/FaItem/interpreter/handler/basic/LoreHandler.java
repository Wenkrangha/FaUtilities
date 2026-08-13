package com.wenkrang.faClip.module.FaItem.interpreter.handler.basic;

import com.wenkrang.faClip.FaClip;
import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.FaMessage.Helper.I18nHelper;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class LoreHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "lore";
    }

    @Override
    public void handle(FaItem faItem, YamlConfiguration yamlConfiguration, FaItemInterpreter faItemInterpreter) {
        try {
            List<String> lore = yamlConfiguration.getStringList(getNode());

            if (!lore.isEmpty()) {
                ItemMeta itemMeta = faItem.getItemMeta();
                itemMeta.setLore(lore);
                faItem.setItemMeta(itemMeta);
            }
        } catch (Exception e) {
            I18nHelper.fw("FaItem.Exception.FaItemInterpreter.CannotFoundNode", getNode());
            if (FaClip.debugger != null) e.printStackTrace();
        }
    }
}
