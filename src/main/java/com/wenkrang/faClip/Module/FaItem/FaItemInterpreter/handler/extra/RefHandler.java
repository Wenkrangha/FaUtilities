package com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.handler.extra;

import com.wenkrang.faClip.Module.FaItem.FaItem;
import com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.FaItemInterpreter;
import com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.handler.FaItemHandler;
import com.wenkrang.faClip.Module.FaItem.TagMgr;
import org.bukkit.configuration.file.YamlConfiguration;

public class RefHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "ref";
    }

    @Override
    public void handle(FaItem faItem, YamlConfiguration yamlConfiguration, FaItemInterpreter faItemInterpreter) {
        String ref = yamlConfiguration.getString(getNode());

        if (ref != null) {
            TagMgr tagMgr = faItem.getTagMgr();

            tagMgr.set(getNode(), ref);
        }
    }
}
