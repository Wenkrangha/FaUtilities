package com.wenkrang.faClip.Module.FaWindow.interpreter.handler.basic;

import com.wenkrang.faClip.Module.FaData.FaData;
import com.wenkrang.faClip.Module.FaMessage.Helper.I18nHelper;
import com.wenkrang.faClip.Module.FaWindow.FaInventory;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.FaInvHandler;

import java.util.ArrayList;
import java.util.List;

public class InvDesignHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        List<String> design = faData.getStringList("design");

        if (design != null) {
            int count = 0;
            for (String s : design) {
                count += s.length();
            }

            if (count != faInventory.size) {
                throw new RuntimeException(I18nHelper.t("FaWindow.Exception.FaInvInterpreter.DesignLengthNotEqualToSize"
                        + faData.getFile().getPath()));
            }

            faInventory.design = new ArrayList<>(design);
        }else {
            throw new RuntimeException(I18nHelper.t("FaWindow.Exception.FaInvInterpreter.DesignNotFound"
                    + faData.getFile().getPath()));
        }
    }
}
