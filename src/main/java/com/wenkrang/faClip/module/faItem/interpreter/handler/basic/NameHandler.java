package com.wenkrang.faClip.module.faItem.interpreter.handler.basic;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faItem.FaItem;
import com.wenkrang.faClip.module.faItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.faItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.faMessage.helper.I18nHelper;
import org.bukkit.inventory.meta.ItemMeta;

public class NameHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "name";
    }

    @Override
    public void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter) {
        ItemMeta itemMeta = faItem.getItemMeta();

        String name = faData.getString(getNode());

        if (name != null) {
            itemMeta.setDisplayName(name);

            faItem.setItemMeta(itemMeta);
        }else {
            I18nHelper.fw("FaItem.Exception.FaItemInterpreter.CannotFoundNode", getNode());
        }
    }
}
