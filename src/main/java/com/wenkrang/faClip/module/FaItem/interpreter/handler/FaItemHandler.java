package com.wenkrang.faClip.module.FaItem.interpreter.handler;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;

public interface FaItemHandler {
    String getNode();
    void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter);
}
