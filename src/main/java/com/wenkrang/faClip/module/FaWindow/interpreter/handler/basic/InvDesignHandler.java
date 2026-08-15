package com.wenkrang.faClip.module.FaWindow.interpreter.handler.basic;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaMessage.exception.FaDataParseException;
import com.wenkrang.faClip.module.FaWindow.FaInventory;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.interpreter.handler.FaInvHandler;

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
                throw new FaDataParseException(faData, String.valueOf(count),
                        "FaWindow.Exception.FaInvInterpreter.DesignLengthNotEqualToSize");
            }

            faInventory.design(design);
        }else if (!faData.has("template")) {
            throw new FaDataParseException(faData, "design", "FaWindow.Exception.FaInvInterpreter.DesignNotFound");
        }
    }
}
