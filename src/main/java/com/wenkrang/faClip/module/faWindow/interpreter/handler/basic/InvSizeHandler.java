package com.wenkrang.faClip.module.faWindow.interpreter.handler.basic;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faMessage.exception.FaDataParseException;
import com.wenkrang.faClip.module.faWindow.FaInventory;
import com.wenkrang.faClip.module.faWindow.FaWindowInstance;
import com.wenkrang.faClip.module.faWindow.interpreter.handler.FaInvHandler;

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
