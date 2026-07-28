package com.wenkrang.faClip.Module.FaDebugger.module;

import com.wenkrang.faClip.Helper.VersionHelper;
import com.wenkrang.faClip.Module.FaCommand.Annotation.Cmd;
import com.wenkrang.faClip.Module.FaCommand.Annotation.Debug;
import com.wenkrang.faClip.Module.FaCommand.Annotation.RequireOP;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faClip.Module.FaMessage.Fm;
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
