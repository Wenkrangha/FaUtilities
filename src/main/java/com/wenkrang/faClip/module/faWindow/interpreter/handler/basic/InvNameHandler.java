package com.wenkrang.faClip.module.faWindow.interpreter.handler.basic;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faMessage.exception.FaDataParseException;
import com.wenkrang.faClip.module.faWindow.FaInventory;
import com.wenkrang.faClip.module.faWindow.FaWindowInstance;
import com.wenkrang.faClip.module.faWindow.interpreter.handler.FaInvHandler;

public class InvNameHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("name")) {
            faInventory.name = faData.getString("name");
        }else if (!faData.has("template")){
            throw new FaDataParseException(faData, "name", "FaWindow.Exception.FaInvInterpreter.NameNotFound");
        }
    }
}
