package com.wenkrang.faClip.module.FaWindow.interpreter.handler.basic;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaMessage.Helper.I18nHelper;
import com.wenkrang.faClip.module.FaWindow.FaInventory;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.interpreter.handler.FaInvHandler;

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
        }else if (!faData.has("template")) {
            throw new RuntimeException(I18nHelper.t("FaWindow.Exception.FaInvInterpreter.SizeNotFound"
                    + faData.getFile().getPath()));
        }
    }
}
