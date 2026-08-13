package com.wenkrang.faClip.module.FaItem.interpreter.handler.extra;

import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class GroupHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "group";
    }

    @Override
    public void handle(FaItem faItem, YamlConfiguration yamlConfiguration, FaItemInterpreter faItemInterpreter) {
        List<String> groups = yamlConfiguration.getStringList(getNode());

        if (!groups.isEmpty()) faItem.setGroup(groups);
    }
}
