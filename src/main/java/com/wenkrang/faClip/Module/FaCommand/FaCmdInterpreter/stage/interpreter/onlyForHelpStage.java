package com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.interpreter;

import com.wenkrang.faClip.Module.FaCommand.FaCmd;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.SimpleStage;
import com.wenkrang.faClip.Module.FaCommand.FaHelperGenerator.FaHelperGenerator;

import java.util.List;

public class onlyForHelpStage implements SimpleStage {
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
