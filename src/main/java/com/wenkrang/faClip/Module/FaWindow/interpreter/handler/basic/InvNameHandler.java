package com.wenkrang.faClip.Module.FaWindow.interpreter.handler.basic;

import com.wenkrang.faClip.Module.FaData.FaData;
import com.wenkrang.faClip.Module.FaMessage.Helper.I18nHelper;
import com.wenkrang.faClip.Module.FaWindow.FaInventory;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.FaInvHandler;

public class InvNameHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("name")) {
            faInventory.name = faData.getString("name");
        }else {
            throw new RuntimeException(I18nHelper.t("FaWindow.Exception.FaInvInterpreter.NameNotFound"
            + faData.getFile().getPath()));
        }
    }
}
