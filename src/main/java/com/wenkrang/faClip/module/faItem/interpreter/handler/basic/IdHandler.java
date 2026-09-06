package com.wenkrang.faClip.module.faItem.interpreter.handler.basic;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faItem.FaItem;
import com.wenkrang.faClip.module.faItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.faItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.faItem.TagMgr;
import com.wenkrang.faClip.module.faMessage.exception.FaDataParseException;
import org.bukkit.NamespacedKey;

public class IdHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "id";
    }

    @Override
    public void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter) {
        String id = faData.getString(getNode());

        if (id != null) {
            faItem.setNamespacedKey(new NamespacedKey(faItem.plugin, id));

            TagMgr tagMgr = new TagMgr(faItemInterpreter.plugin ,faItem);

            tagMgr.set("id", id);
        }else {
            throw new FaDataParseException(faData, getNode());
        }
    }
}
