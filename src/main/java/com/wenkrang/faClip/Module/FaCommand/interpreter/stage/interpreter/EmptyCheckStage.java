package com.wenkrang.faClip.module.FaCommand.interpreter.stage.interpreter;

import com.wenkrang.faClip.module.FaCommand.FaCmd;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import com.wenkrang.faClip.module.FaCommand.interpreter.stage.SimpleStage;
import com.wenkrang.faClip.module.FaMessage.Fm;

import java.util.List;

import static com.wenkrang.faClip.module.FaMessage.Helper.I18nHelper.t;

public class EmptyCheckStage implements SimpleStage {
    @Override
    public boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds) {
        if (faCmds.isEmpty()) {
            Fm.error(faCmdContext.sender(), t("FaCommand.Error.Interpreter.NotFound"));
            return false;
        }
        return true;
    }
}
