package com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.interpreter;

import com.wenkrang.faClip.Module.FaCommand.FaCmd;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.SimpleStage;
import com.wenkrang.faClip.Module.FaMessage.Fm;

import java.util.List;

import static com.wenkrang.faClip.Module.FaMessage.Helper.I18nHelper.t;

public class ConflictCheckStage implements SimpleStage {
    @Override
    public boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds) {
        if (cmd == null) return false;
        if (faCmds.size() > 1) {
            Fm.error(faCmdContext.sender(), t("FaCommand.Error.Interpreter.Conflict") + " "
                    + faCmds.stream().map(FaCmd::getNode).toList());
            return false;
        }
        return true;
    }
}
