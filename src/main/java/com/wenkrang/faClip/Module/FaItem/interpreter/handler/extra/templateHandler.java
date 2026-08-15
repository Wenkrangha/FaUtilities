package com.wenkrang.faClip.module.FaItem.interpreter.handler.extra;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;

public class TemplateHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "template";
    }

    @Override
    public void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter) {
        if (faData.getString("template") != null) {
            String template = faData.getString("template");
            faItem.setTemplate(template);

            faData.template(FaData.getPluginResource(template, faItemInterpreter.plugin));
        }
    }
}
