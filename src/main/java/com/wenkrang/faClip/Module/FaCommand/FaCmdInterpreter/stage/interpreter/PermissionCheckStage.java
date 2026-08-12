package com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.interpreter;

import com.wenkrang.faClip.Module.FaCommand.FaCmd;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.SimpleStage;
import com.wenkrang.faClip.Module.FaMessage.Fm;

import java.util.List;

import static com.wenkrang.faClip.Module.FaMessage.Helper.I18nHelper.t;

public class PermissionCheckStage implements SimpleStage {
    @Override
    public boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds) {
        if (cmd == null) return false;
        if (cmd.getPermission() != null && !faCmdContext.sender().hasPermission(cmd.getPermission())) {
            Fm.log(faCmdContext.sender(), t("FaCommand.Error.Interpreter.NoPermission"));
            return false;
        }
        return true;
    }
}
