package com.wenkrang.faClip.Module.FaWindow.interpreter.handler.extra;

import com.wenkrang.faClip.Module.FaData.FaData;
import com.wenkrang.faClip.Module.FaItem.FaItem;
import com.wenkrang.faClip.Module.FaItem.FaItemInstance;
import com.wenkrang.faClip.Module.FaWindow.FaInventory;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.FaInvHandler;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public class InvTagHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        ConfigurationSection tag = faData.getSection("tag");

        if (tag != null) {
            Set<String> keys = tag.getKeys(false);

            for (String key : keys) {
                List<Integer> integerList = tag.getIntegerList(key);

                faInventory.tag.put(key, integerList);
            }
        }
    }
}
