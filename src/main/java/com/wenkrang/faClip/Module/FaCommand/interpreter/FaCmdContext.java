package com.wenkrang.faClip.module.FaCommand.interpreter;

import com.wenkrang.faClip.module.FaInterface.FaIntfContext;
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
