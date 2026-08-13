package com.wenkrang.faClip.module.FaDebugger.module;

import com.wenkrang.faClip.helper.VersionHelper;
import com.wenkrang.faClip.module.FaCommand.annotation.Cmd;
import com.wenkrang.faClip.module.FaCommand.annotation.Debug;
import com.wenkrang.faClip.module.FaCommand.annotation.RequireOP;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import com.wenkrang.faClip.module.FaMessage.Fm;
import org.bukkit.Bukkit;

public class FaVersionDebugger {
    @Cmd("fatest.version.isbelow")
    @Debug
    @RequireOP
    public static void isBelow(String version, FaCmdContext ctx) {
        boolean below = VersionHelper.isBelow(version);

        Fm.info(ctx.sender(), String.valueOf(below));
    }

    @Cmd("fatest.version.get")
    @Debug
    @RequireOP
    public static void version(FaCmdContext ctx) {
        Fm.info(ctx.sender(), Bukkit.getVersion());
    }
}
