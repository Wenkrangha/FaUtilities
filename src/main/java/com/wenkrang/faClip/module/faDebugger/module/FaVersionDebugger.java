package com.wenkrang.faClip.module.faDebugger.module;

import com.wenkrang.faClip.helper.VersionHelper;
import com.wenkrang.faClip.module.faCommand.annotation.Cmd;
import com.wenkrang.faClip.module.faCommand.annotation.Debug;
import com.wenkrang.faClip.module.faCommand.annotation.RequireOP;
import com.wenkrang.faClip.module.faCommand.interpreter.FaCmdContext;
import com.wenkrang.faClip.module.faMessage.Fm;
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
