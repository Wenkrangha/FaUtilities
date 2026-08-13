package com.wenkrang.faClip.module.FaCommand.interpreter.stage.tabComplete;

import com.wenkrang.faClip.module.FaCommand.FaCmd;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import com.wenkrang.faClip.module.FaCommand.interpreter.stage.SimpleStage;

import java.util.List;

public class TabPermissionCheckStage implements SimpleStage {
    @Override
    public boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds) {
        return !(cmd.getPermission() != null && !faCmdContext.sender().hasPermission(cmd.getPermission()));
    }
}
