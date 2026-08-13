package com.wenkrang.faClip.module.FaWindow.interpreter.handler.basic;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaMessage.Helper.I18nHelper;
import com.wenkrang.faClip.module.FaWindow.FaInventory;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.interpreter.handler.FaInvHandler;

public class InvIdHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("id")) {
            faInventory.id = faData.getString("id");
        }else {
            throw new RuntimeException(I18nHelper.t("FaWindow.Exception.FaInvInterpreter.IdNotFound"
            + faData.getFile().getPath()));
        }
    }
}
