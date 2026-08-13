package com.wenkrang.faClip.module.FaItem.interpreter.handler.basic;

import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.FaMessage.Helper.I18nHelper;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.meta.ItemMeta;

public class NameHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "name";
    }

    @Override
    public void handle(FaItem faItem, YamlConfiguration yamlConfiguration, FaItemInterpreter faItemInterpreter) {
        ItemMeta itemMeta = faItem.getItemMeta();

        String name = yamlConfiguration.getString(getNode());

        if (name != null) {
            itemMeta.setDisplayName(name);

            faItem.setItemMeta(itemMeta);
        }else {
            I18nHelper.fw("FaItem.Exception.FaItemInterpreter.CannotFoundNode", getNode());
        }
    }
}
