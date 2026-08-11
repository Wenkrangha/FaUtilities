package com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter;

import com.wenkrang.faClip.Module.FaInterface.FaIntfContext;
import org.bukkit.command.CommandSender;

public class FaCmdContext extends FaIntfContext {
    public FaCmdContext(CommandSender sender, String[] args) {
        super();
        set("sender", sender);
        set("args", args);
    }

    public CommandSender sender() {
        return get("sender");
    }

    public String[] args() {
        return get("args");
    }
}
