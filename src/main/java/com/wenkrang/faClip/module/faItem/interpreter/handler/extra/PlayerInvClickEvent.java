package com.wenkrang.faClip.module.faItem.interpreter.handler.extra;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faItem.FaItem;
import com.wenkrang.faClip.module.faItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.faItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.faItem.TagMgr;

public class PlayerInvClickEvent implements FaItemHandler {
    @Override
    public String getNode() {
        return "event.inv_click";
    }

    @Override
    public void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter) {
        String string = faData.getString(getNode());

        if (string != null) {
            TagMgr tagMgr = new TagMgr(faItemInterpreter.plugin, faItem);

            tagMgr.set(getNode(), string);
        }
    }
}
