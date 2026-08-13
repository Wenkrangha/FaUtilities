package com.wenkrang.faClip.module.FaItem.interpreter.handler.extra;

import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.FaItem.TagMgr;
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
