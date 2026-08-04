package com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.handler;

import com.wenkrang.faClip.Module.FaItem.FaItem;
import com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.FaItemInterpreter;
import com.wenkrang.faClip.Module.FaItem.tagMgr;
import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerItemClickEvent implements FaItemHandler {
    @Override
    public String getNode() {
        return "event.item_click";
    }

    @Override
    public void handle(FaItem faItem, YamlConfiguration yamlConfiguration, FaItemInterpreter faItemInterpreter) {
        String string = yamlConfiguration.getString(getNode());

        if (string != null) {
            tagMgr tagMgr = new tagMgr(faItemInterpreter.plugin, faItem);

            tagMgr.set(getNode(), string);
        }
    }
}
