package com.wenkrang.faClip.module.faItem.interpreter.handler.extra;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faItem.FaItem;
import com.wenkrang.faClip.module.faItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.faItem.interpreter.handler.FaItemHandler;

public class IsolateHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "isolate";
    }

    @Override
    public void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter) {
        boolean bool = faData.getBoolean(getNode());
        if (bool) faItem.setIsolate(true);
    }
}
