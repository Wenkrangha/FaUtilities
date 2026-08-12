package com.wenkrang.faClip.Module.FaWindow.interpreter.handler.extra;

import com.wenkrang.faClip.Module.FaData.FaData;
import com.wenkrang.faClip.Module.FaWindow.FaInventory;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.FaInvHandler;

public class InvMoveableHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("moveable")) {
            Object o = faData.get("moveable");

            faInventory.setMoveableSlots(o);
        }
    }
}
