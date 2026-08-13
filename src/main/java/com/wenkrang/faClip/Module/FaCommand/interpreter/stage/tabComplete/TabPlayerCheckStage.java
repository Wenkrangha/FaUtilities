package com.wenkrang.faClip.module.FaCommand.interpreter.stage.tabComplete;

import com.wenkrang.faClip.module.FaCommand.FaCmd;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import com.wenkrang.faClip.module.FaCommand.interpreter.stage.SimpleStage;
import org.bukkit.entity.Player;

import java.util.List;

public class TabPlayerCheckStage implements SimpleStage {
    @Override
    public boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds) {
        return !cmd.isForPlayer() || faCmdContext.sender() instanceof Player;
    }
}
