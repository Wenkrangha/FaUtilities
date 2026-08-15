package com.wenkrang.faClip.module.FaWindow.interpreter.handler.basic;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaMessage.exception.FaDataParseException;
import com.wenkrang.faClip.module.FaWindow.FaInventory;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.interpreter.handler.FaInvHandler;

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
