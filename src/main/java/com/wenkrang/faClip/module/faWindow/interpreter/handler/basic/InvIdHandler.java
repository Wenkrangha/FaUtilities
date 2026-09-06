package com.wenkrang.faClip.module.faWindow.interpreter.handler.basic;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faMessage.exception.FaDataParseException;
import com.wenkrang.faClip.module.faWindow.FaInventory;
import com.wenkrang.faClip.module.faWindow.FaWindowInstance;
import com.wenkrang.faClip.module.faWindow.interpreter.handler.FaInvHandler;

public class InvIdHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("id")) {
            faInventory.id = faData.getString("id");
        }else {
            throw new FaDataParseException(faData, "id", "FaWindow.Exception.FaInvInterpreter.IdNotFound");
        }
    }
}
