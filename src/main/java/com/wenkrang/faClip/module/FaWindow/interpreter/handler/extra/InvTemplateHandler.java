package com.wenkrang.faClip.module.FaWindow.interpreter.handler.extra;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaWindow.FaInventory;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.interpreter.handler.FaInvHandler;

public class InvTemplateHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("template")) {
            String templateID = faData.getString("template");

            faWindowInstance.load(templateID);

            FaInventory src = faWindowInstance.getFaInventory(templateID);

            if (src != null) {
                FaInventory.clone(faInventory, src);
            }

            faInventory.template = templateID;
        }
    }
}
