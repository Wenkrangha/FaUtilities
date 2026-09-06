package com.wenkrang.faClip.module.faWindow.interpreter.handler.extra;

import com.wenkrang.faClip.module.faData.FaData;
import com.wenkrang.faClip.module.faWindow.FaInventory;
import com.wenkrang.faClip.module.faWindow.FaWindowInstance;
import com.wenkrang.faClip.module.faWindow.helper.WinDataHelper;
import com.wenkrang.faClip.module.faWindow.interpreter.handler.FaInvHandler;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class InvNoteHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("note")) {
            ConfigurationSection note = faData.getSection("note");

            if (note != null) {
                for (String key : note.getKeys(false)) {
                    // 获取设计
                    char[] design = WinDataHelper.getDesignArray(faData.getStringList("design"));

                    Object o = note.get(key);

                    // 判断类型
                    // 判断是否为列表
                    if (o instanceof List<?> list) {
                        // 判断列表第一个元素是否为整数
                        if (list.getFirst() instanceof Integer) {
                            List<Integer> slots = (List<Integer>) list;

                            faInventory.note(key, slots);
                        }else if (list.getFirst() instanceof String) {
                            // 取出所有标签头
                            List<String> slotsList = note.getStringList(key);

                            ArrayList<Integer> slots = new ArrayList<>();

                            for (String noteKey : slotsList) {
                                slots.addAll(WinDataHelper.getSlotsByID(design, noteKey));
                            }

                            faInventory.note(key, slots);
                        }
                    }else if (o instanceof String) {
                        faInventory.note(key, WinDataHelper.getSlotsByID(design, (String) o));
                    }
                }
            }
        }
    }
}
