package com.wenkrang.faClip.Module.FaDebugger.module;

import com.wenkrang.faClip.FaClip;
import com.wenkrang.faClip.Module.FaCommand.Annotation.Cmd;
import com.wenkrang.faClip.Module.FaCommand.Annotation.Debug;
import com.wenkrang.faClip.Module.FaCommand.Annotation.ForPlayer;
import com.wenkrang.faClip.Module.FaCommand.Annotation.RequireOP;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faClip.Module.FaWindow.FaWindowInstance;

public class FaInvDebugger {
    public static FaWindowInstance faWindowInstance;

    @Cmd("fatest.inv.load")
    @RequireOP
    @Debug
    public static void load() {
        faWindowInstance = new FaWindowInstance(FaClip.plugin, FaItemEventDebugger.faItemInstance);

        faWindowInstance.loadAll();

        faWindowInstance.autoRegister();
    }

    @Cmd("fatest.inv.open")
    @RequireOP
    @Debug
    @ForPlayer
    public static void open(FaCmdContext faCmdContext){

    }
}
