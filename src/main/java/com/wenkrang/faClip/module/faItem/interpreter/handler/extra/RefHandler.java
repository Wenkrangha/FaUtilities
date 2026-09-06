package com.wenkrang.faClip.module.faItem.interpreter.handler.extra;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faItem.FaItem;
import com.wenkrang.faClip.module.faItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.faItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.faItem.TagMgr;

public class RefHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "ref";
    }

    @Override
    public void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter) {
        String ref = faData.getString(getNode());

        if (ref != null) {
            TagMgr tagMgr = faItem.getTagMgr();

            tagMgr.set(getNode(), ref);
        }
    }
}
