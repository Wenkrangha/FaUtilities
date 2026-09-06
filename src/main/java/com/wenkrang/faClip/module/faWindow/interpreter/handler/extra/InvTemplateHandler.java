package com.wenkrang.faClip.module.faWindow.interpreter.handler.extra;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faWindow.FaInventory;
import com.wenkrang.faClip.module.faWindow.FaWindowInstance;
import com.wenkrang.faClip.module.faWindow.interpreter.handler.FaInvHandler;

public class InvTemplateHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("template")) {
            String templatePath = faData.getString("template");

            faData.template(FaData.getPluginResource(templatePath, faWindowInstance.getPlugin()));

            faInventory.template = templatePath;
        }
    }
}
