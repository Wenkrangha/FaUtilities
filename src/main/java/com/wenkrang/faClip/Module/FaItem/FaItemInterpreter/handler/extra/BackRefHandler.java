package com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.handler.extra;

import com.wenkrang.faClip.Module.FaItem.FaItem;
import com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.FaItemInterpreter;
import com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.handler.FaItemHandler;
import com.wenkrang.faClip.Module.FaItem.TagMgr;
import org.bukkit.configuration.file.YamlConfiguration;

public class BackRefHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "backref";
    }

    @Override
    public void handle(FaItem faItem, YamlConfiguration yamlConfiguration, FaItemInterpreter faItemInterpreter) {
        boolean backref = yamlConfiguration.getBoolean(getNode());

        TagMgr tagMgr = faItem.getTagMgr();

        if (backref) tagMgr.set(getNode(), String.valueOf(true));
    }
}
