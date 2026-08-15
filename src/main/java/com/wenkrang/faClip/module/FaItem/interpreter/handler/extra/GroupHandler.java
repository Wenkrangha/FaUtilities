package com.wenkrang.faClip.module.FaItem.interpreter.handler.extra;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;

import java.util.List;

public class GroupHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "group";
    }

    @Override
    public void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter) {
        List<String> groups = faData.getStringList(getNode());

        if (!groups.isEmpty()) faItem.setGroup(groups);
    }
}
