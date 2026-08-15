package com.wenkrang.faClip.module.FaWindow.interpreter.handler.basic;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaMessage.exception.FaDataParseException;
import com.wenkrang.faClip.module.FaWindow.FaInventory;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.interpreter.handler.FaInvHandler;

public class InvSizeHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("size")) {
            int size = faData.getInt("size");

            if (size % 9 != 0) {
                throw new FaDataParseException(faData, String.valueOf(size),
                        "FaWindow.Exception.FaInvInterpreter.SizeNotMultipleOf9");
            }

            faInventory.size = size;
        }else if (!faData.has("template")) {
            throw new FaDataParseException(faData, "size", "FaWindow.Exception.FaInvInterpreter.SizeNotFound");
        }
    }
}
