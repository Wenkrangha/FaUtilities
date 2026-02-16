package com.wenkrang.faUtilities;

import com.wenkrang.faUtilities.Moudle.FaCommand.AnnotationHandler.CmdNodeHandler;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInstance;
import org.bukkit.plugin.java.JavaPlugin;

public final class FaUtilities extends JavaPlugin {

    public static FaCmdInstance faCmdInstance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        faCmdInstance = FaCmdInstance.create(this);
        faCmdInstance.enableForAll(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        faCmdInstance.close();
    }


}
