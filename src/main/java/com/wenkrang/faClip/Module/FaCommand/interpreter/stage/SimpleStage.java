package com.wenkrang.faClip.module.FaCommand.interpreter.stage;

import com.wenkrang.faClip.module.FaCommand.FaCmd;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;

import java.util.List;

public interface SimpleStage {
    boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds);
}
