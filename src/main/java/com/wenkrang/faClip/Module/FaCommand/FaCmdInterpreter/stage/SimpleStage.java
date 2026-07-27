package com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage;

import com.wenkrang.faClip.Module.FaCommand.FaCmd;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;

import java.util.List;

public interface SimpleStage {
    boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds);
}
