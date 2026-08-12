package com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.tabComplete;

import com.wenkrang.faClip.Module.FaCommand.FaCmd;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.SimpleStage;

import java.util.List;

public class TabPermissionCheckStage implements SimpleStage {
    @Override
    public boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds) {
        return !(cmd.getPermission() != null && !faCmdContext.sender().hasPermission(cmd.getPermission()));
    }
}
