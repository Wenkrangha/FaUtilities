package com.wenkrang.faClip.module.FaWindow.interpreter.handler.extra;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaWindow.FaInventory;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.interpreter.handler.FaInvHandler;

public class InvLockHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        faInventory.lock = faData.getBoolean("lock");
    }
}
