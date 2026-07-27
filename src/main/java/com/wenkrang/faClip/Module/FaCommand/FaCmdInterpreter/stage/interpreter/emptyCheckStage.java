package com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.interpreter;

import com.wenkrang.faClip.Module.FaCommand.FaCmd;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.SimpleStage;
import com.wenkrang.faClip.Module.FaMessage.Fm;

import java.util.List;

import static com.wenkrang.faClip.Module.FaMessage.Helper.i18nHelper.t;

public class emptyCheckStage implements SimpleStage {
    @Override
    public boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds) {
        if (faCmds.isEmpty()) {
            Fm.error(faCmdContext.sender(), t("FaCommand.Error.Interpreter.NotFound"));
            return false;
        }
        return true;
    }
}
