package com.wenkrang.faClip.module.FaItem.interpreter.handler.basic;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.FaItem.TagMgr;
import com.wenkrang.faClip.module.FaMessage.exception.FaDataParseException;
import com.wenkrang.faClip.module.FaMessage.helper.I18nHelper;
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
