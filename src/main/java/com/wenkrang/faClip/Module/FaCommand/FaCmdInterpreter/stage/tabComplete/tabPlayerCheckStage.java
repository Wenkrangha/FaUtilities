package com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.tabComplete;

import com.wenkrang.faClip.Module.FaCommand.FaCmd;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.SimpleStage;
import org.bukkit.entity.Player;

import java.util.List;

public class tabPlayerCheckStage implements SimpleStage {
    @Override
    public boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds) {
        return !cmd.isForPlayer() || faCmdContext.sender() instanceof Player;
    }
}
