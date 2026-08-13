package com.wenkrang.faClip.module.FaCommand.interpreter.stage.interpreter;

import com.wenkrang.faClip.module.FaCommand.FaCmd;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import com.wenkrang.faClip.module.FaCommand.interpreter.stage.SimpleStage;
import com.wenkrang.faClip.module.FaCommand.helperGenerator.FaHelperGenerator;

import java.util.List;

public class OnlyForHelpStage implements SimpleStage {
    @Override
    public boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds) {
        if (cmd == null) return false;
        if (cmd.isOnlyForHelp()) {
            FaHelperGenerator faHelperGenerator = new FaHelperGenerator(cmd.getFaCmdInstance());
            for (String help : faHelperGenerator.generate(cmd.getNode())) {
                faCmdContext.sender().sendMessage(help);
            }
            return false;
        }
        return true;
    }
}
