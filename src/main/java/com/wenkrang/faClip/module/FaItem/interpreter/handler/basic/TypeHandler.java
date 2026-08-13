package com.wenkrang.faClip.module.FaItem.interpreter.handler.basic;

import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.FaMessage.Helper.I18nHelper;
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
