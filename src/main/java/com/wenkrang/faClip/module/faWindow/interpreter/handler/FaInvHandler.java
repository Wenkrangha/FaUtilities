package com.wenkrang.faClip.module.faWindow.interpreter.handler;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faWindow.FaInventory;
import com.wenkrang.faClip.module.faWindow.FaWindowInstance;

public interface FaInvHandler {
    void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance);
}
