package com.wenkrang.faClip.Module.FaWindow.interpreter.handler;

import com.wenkrang.faClip.Module.FaData.FaData;
import com.wenkrang.faClip.Module.FaWindow.FaInventory;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;

public interface FaInvHandler {
    void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance);
}
