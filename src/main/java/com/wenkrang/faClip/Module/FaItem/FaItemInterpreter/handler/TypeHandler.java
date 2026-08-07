package com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.handler;

import com.wenkrang.faClip.Module.FaItem.FaItem;
import com.wenkrang.faClip.Module.FaItem.FaItemInterpreter.FaItemInterpreter;
import com.wenkrang.faClip.Module.FaMessage.Helper.I18nHelper;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

public class TypeHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "type";
    }

    @Override
    public void handle(FaItem faItem, YamlConfiguration yamlConfiguration, FaItemInterpreter faItemInterpreter) {
        String type = yamlConfiguration.getString(getNode());

        if (type != null) {
            Material material = Material.valueOf(type);
            faItem.setType(material);
        }else {
            I18nHelper.fw("FaItem.Exception.FaItemInterpreter.CannotFoundNode", getNode());
        }
    }
}
