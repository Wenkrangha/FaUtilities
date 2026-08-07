package com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.handler;

import com.wenkrang.faClip.Module.FaItem.FaItem;
import com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.FaItemInterpreter;
import com.wenkrang.faClip.Module.FaItem.TagMgr;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInvClickEvent implements FaItemHandler{
    @Override
    public String getNode() {
        return "event.inv_click";
    }

    @Override
    public void handle(FaItem faItem, YamlConfiguration yamlConfiguration, FaItemInterpreter faItemInterpreter) {
        String string = yamlConfiguration.getString(getNode());

        if (string != null) {
            TagMgr tagMgr = new TagMgr(faItemInterpreter.plugin, faItem);

            tagMgr.set(getNode(), string);
        }
    }
}
