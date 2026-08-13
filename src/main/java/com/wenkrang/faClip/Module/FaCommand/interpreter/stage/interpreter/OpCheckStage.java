package com.wenkrang.faClip.module.FaCommand.interpreter.stage.interpreter;

import com.wenkrang.faClip.module.FaCommand.FaCmd;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import com.wenkrang.faClip.module.FaCommand.interpreter.stage.SimpleStage;
import com.wenkrang.faClip.module.FaMessage.Fm;

import java.util.List;

import static com.wenkrang.faClip.module.FaMessage.Helper.I18nHelper.t;

public class OpCheckStage implements SimpleStage {
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
