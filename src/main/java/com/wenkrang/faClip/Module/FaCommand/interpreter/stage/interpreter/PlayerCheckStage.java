package com.wenkrang.faClip.module.FaCommand.interpreter.stage.interpreter;

import com.wenkrang.faClip.module.FaCommand.FaCmd;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import com.wenkrang.faClip.module.FaCommand.interpreter.stage.SimpleStage;
import com.wenkrang.faClip.module.FaMessage.Fm;
import org.bukkit.entity.Player;

import java.util.List;

import static com.wenkrang.faClip.module.FaMessage.Helper.I18nHelper.t;

public class PlayerCheckStage implements SimpleStage {
    @Override
    public boolean check(FaCmd cmd, FaCmdContext faCmdContext, List<FaCmd> faCmds) {
        if (cmd == null) return false;
        if (cmd.isForPlayer() && !(faCmdContext.sender() instanceof Player)) {
            Fm.error(t("FaCommand.Error.Interpreter.OnlyForPlayer"));
            return false;
        }
        return true;
    }
}
