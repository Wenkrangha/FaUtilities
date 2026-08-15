package com.wenkrang.faClip.module.FaWindow.interpreter.handler.basic;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.FaItemInstance;
import com.wenkrang.faClip.module.FaMessage.Fm;
import com.wenkrang.faClip.module.FaWindow.FaInventory;
import com.wenkrang.faClip.module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.module.FaWindow.interpreter.handler.FaInvHandler;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class InvDefineHandler implements FaInvHandler {


    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        ConfigurationSection define = faData.getSection("define");

        if (define != null) {
            Set<String> keys = define.getKeys(false);

            for (String key : keys) {
                String value = define.getString(key);

                // 转换物品
                ItemStack itemStack = faWindowInstance.getFaItemInstance().convertDefine(value);

                faInventory.define(key, itemStack);
            }
        }
    }
}
