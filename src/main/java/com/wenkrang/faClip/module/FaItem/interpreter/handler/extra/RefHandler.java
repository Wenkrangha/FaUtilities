package com.wenkrang.faClip.module.FaItem.interpreter.handler.extra;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.FaItem.TagMgr;

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
