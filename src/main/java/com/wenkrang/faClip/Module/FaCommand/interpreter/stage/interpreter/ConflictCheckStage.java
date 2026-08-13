package com.wenkrang.faClip.module.FaCommand.interpreter.stage.interpreter;

import com.wenkrang.faClip.module.FaCommand.FaCmd;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import com.wenkrang.faClip.module.FaCommand.interpreter.stage.SimpleStage;
import com.wenkrang.faClip.module.FaMessage.Fm;

import java.util.List;

import static com.wenkrang.faClip.module.FaMessage.Helper.I18nHelper.t;

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
