package com.wenkrang.faClip.module.faItem.interpreter.handler.extra;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faItem.FaItem;
import com.wenkrang.faClip.module.faItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.faItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.faItem.TagMgr;

public class BackRefHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "backref";
    }

    @Override
    public void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter) {
        boolean backref = faData.getBoolean(getNode());

        TagMgr tagMgr = faItem.getTagMgr();

        if (backref) tagMgr.set(getNode(), String.valueOf(true));
    }
}
