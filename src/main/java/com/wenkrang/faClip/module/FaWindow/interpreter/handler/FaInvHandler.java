package com.wenkrang.faClip.module.FaWindow.interpreter.handler;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaWindow.FaInventory;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;

public interface FaInvHandler {
    void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance);
}
