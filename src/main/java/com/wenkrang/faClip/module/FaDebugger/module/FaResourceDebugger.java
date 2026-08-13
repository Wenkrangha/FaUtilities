package com.wenkrang.faClip.module.FaDebugger.module;

import com.wenkrang.faClip.FaClip;
import com.wenkrang.faClip.module.FaCommand.annotation.Cmd;
import com.wenkrang.faClip.module.FaCommand.annotation.Debug;
import com.wenkrang.faClip.module.FaCommand.annotation.RequireOP;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import com.wenkrang.faClip.module.FaMessage.Fm;
import com.wenkrang.faClip.module.FaResource.BukkitResource;
import com.wenkrang.faClip.module.FaResource.FaBukkitResourceManager;
import org.bukkit.entity.Player;

public class FaResourceDebugger {
    @Cmd("fatest.resource")
    @RequireOP
    @Debug
    public static void resource(FaCmdContext ctx) {
        FaBukkitResourceManager faBukkitResourceManager = new FaBukkitResourceManager(FaClip.plugin);

        BukkitResource test = new BukkitResource("test"
                , "https://gitee.com/yuzutan29/OpenNoteBlockStudio/raw/master/datafiles/Data/extranotes.zip"
        ,"609D2EA415906ECBA8171ADE91BDA1C1056244AB");
        faBukkitResourceManager.registerResource(test);

        faBukkitResourceManager.askFor(test, (Player) ctx.sender(), "这是FaClip的测试，没有什么作用");

        Fm.debug("资源包测试");
    }
}
