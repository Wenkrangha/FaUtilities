package com.wenkrang.faClip.module.faWindow.interpreter.handler.extra;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faWindow.FaInventory;
import com.wenkrang.faClip.module.faWindow.FaWindowInstance;
import com.wenkrang.faClip.module.faWindow.interpreter.handler.FaInvHandler;

public class InvMoveableHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("moveable")) {
            Object o = faData.get("moveable");

            faInventory.setMoveableSlots(o);
        }
    }
}
