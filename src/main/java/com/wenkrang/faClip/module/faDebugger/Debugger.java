package com.wenkrang.faClip.module.faDebugger;

import com.wenkrang.faClip.FaClip;
import com.wenkrang.faClip.module.faCommand.FaCmdInstance;

/**
 * 这里是插件的调试类
 */
public class Debugger {

    private final FaCmdInstance faCmdInstance;

    public Debugger() {
        this.faCmdInstance = new FaCmdInstance(FaClip.plugin);

        faCmdInstance.enableForAll(FaClip.plugin);
    }

    public FaCmdInstance getFaCmdInstance() {
        return faCmdInstance;
    }
}
