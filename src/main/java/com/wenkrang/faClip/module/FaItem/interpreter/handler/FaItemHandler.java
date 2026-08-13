package com.wenkrang.faClip.module.FaItem.interpreter.handler;

import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import org.bukkit.configuration.file.YamlConfiguration;

public interface FaItemHandler {
    String getNode();
    void handle(FaItem faItem, YamlConfiguration yamlConfiguration, FaItemInterpreter faItemInterpreter);
}
