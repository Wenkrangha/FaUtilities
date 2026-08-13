package com.wenkrang.faClip.module.FaItem.interpreter.handler.extra;

import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import org.bukkit.configuration.file.YamlConfiguration;

public class IsolateHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "isolate";
    }

    @Override
    public void handle(FaItem faItem, YamlConfiguration yamlConfiguration, FaItemInterpreter faItemInterpreter) {
        boolean bool = yamlConfiguration.getBoolean(getNode());
        if (bool) faItem.setIsolate(true);
    }
}
