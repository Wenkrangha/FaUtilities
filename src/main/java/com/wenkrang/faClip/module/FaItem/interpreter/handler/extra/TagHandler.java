package com.wenkrang.faClip.module.FaItem.interpreter.handler.extra;

import com.wenkrang.faClip.FaClip;
import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.FaItemInterpreter;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.FaItem.TagMgr;
import com.wenkrang.faClip.module.FaMessage.exception.FaDataParseException;
import com.wenkrang.faClip.module.FaMessage.helper.I18nHelper;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Set;

public class TagHandler implements FaItemHandler {
    @Override
    public String getNode() {
        return "tag";
    }

    @Override
    public void handle(FaItem faItem, FaData faData, FaItemInterpreter faItemInterpreter) {

        // 获取标签管理器
        TagMgr tagMgr = faItem.getTagMgr();

        // 获取配置节点
        ConfigurationSection configurationSection = faData.getSection(getNode());


        if (configurationSection != null) {
            // 获取所有节点
            Set<String> keys = configurationSection.getKeys(false);

            // 历遍
            for (String key : keys) {
                // 获取节点下的值
                String value = configurationSection.getString(key);

                // 写入物品的Tag
                tagMgr.set(key, value);
            }
        }

    }
}
