package com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.handler.basic;

import com.wenkrang.faClip.Module.FaItem.FaItem;
import com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.FaItemInterpreter;
import com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.handler.FaItemHandler;
import com.wenkrang.faClip.Module.FaItem.TagMgr;
import com.wenkrang.faClip.Module.FaMessage.Helper.I18nHelper;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;

public class IdHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "id";
    }

    @Override
    public void handle(FaItem faItem, YamlConfiguration yamlConfiguration, FaItemInterpreter faItemInterpreter) {
        String id = yamlConfiguration.getString(getNode());

        if (id != null) {
            faItem.setNamespacedKey(new NamespacedKey(faItem.plugin, id));

            TagMgr tagMgr = new TagMgr(faItemInterpreter.plugin ,faItem);

            tagMgr.set("id", id);
        }else {
            I18nHelper.fw("FaItem.Exception.FaItemInterpreter.CannotFoundNode", getNode());
        }
    }
}
