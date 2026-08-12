package com.wenkrang.faClip.Module.FaWindow.interpreter.handler.extra;

import com.wenkrang.faClip.Module.FaData.FaData;
import com.wenkrang.faClip.Module.FaWindow.FaInventory;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;
import com.wenkrang.faClip.Module.FaWindow.interpreter.handler.FaInvHandler;
import org.bukkit.configuration.ConfigurationSection;

public class InvNoteHandler implements FaInvHandler {
    @Override
    public void handle(FaInventory faInventory, FaData faData, FaWindowInstance faWindowInstance) {
        if (faData.has("note")) {
            ConfigurationSection note = faData.getSection("note");

            if (note != null) {
                for (String key : note.getKeys(false)) {
                    faInventory.note(key, note.getIntegerList(key));
                }
            }
        }
    }
}
