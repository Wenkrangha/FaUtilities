package com.wenkrang.faClip.module.FaItem.interpreter.handler.basic;

import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.FaItem.TagMgr;
import com.wenkrang.faClip.module.FaMessage.Helper.I18nHelper;
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
