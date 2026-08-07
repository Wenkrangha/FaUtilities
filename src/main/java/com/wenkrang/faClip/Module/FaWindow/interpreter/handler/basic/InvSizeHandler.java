package com.wenkrang.faClip.Module.FaWindow.interpreter.handler.basic;

import com.wenkrang.faClip.Module.FaData.FaData;
import com.wenkrang.faClip.Module.FaMessage.Helper.I18nHelper;
import com.wenkrang.faClip.Module.FaWindow.FaInventory;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.FaInvHandler;

public class InvSizeHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("size")) {
            int size = faData.getInt("size");

            if (size % 9 != 0) {
                throw new RuntimeException(I18nHelper.t("FaWindow.Exception.FaInvInterpreter.SizeNotMultipleOf9"
                        + faData.getFile().getPath()));
            }

            faInventory.size = size;
        }else {
            throw new RuntimeException(I18nHelper.t("FaWindow.Exception.FaInvInterpreter.SizeNotFound"
                    + faData.getFile().getPath()));
        }
    }
}
