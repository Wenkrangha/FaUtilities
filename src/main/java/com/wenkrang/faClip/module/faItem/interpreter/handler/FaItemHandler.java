package com.wenkrang.faClip.module.faItem.interpreter.handler;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faItem.FaItem;
import com.wenkrang.faClip.module.faItem.interpreter.FaItemInterpreter;

public interface FaItemHandler {
    String getNode();
    void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter);
}
