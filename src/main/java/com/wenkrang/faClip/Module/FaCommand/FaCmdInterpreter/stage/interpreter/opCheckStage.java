package com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.interpreter;

import com.wenkrang.faClip.Module.FaCommand.FaCmd;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.SimpleStage;
import com.wenkrang.faClip.Module.FaMessage.Fm;

import java.util.List;

import static com.wenkrang.faClip.Module.FaMessage.Helper.i18nHelper.t;

public class opCheckStage implements SimpleStage {
    @Override
    public boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds) {
        if (cmd == null) return false;
        if (cmd.isRequireOP() && !faCmdContext.sender().isOp()) {
            Fm.log(faCmdContext.sender(), t("FaCommand.Error.Interpreter.RequireOP"));
            return false;
        }
        return true;
    }
}
