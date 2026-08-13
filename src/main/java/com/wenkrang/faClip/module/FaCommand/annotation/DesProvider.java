package com.wenkrang.faClip.module.FaCommand.annotation;

import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import org.jetbrains.annotations.NotNull;

public interface DesProvider {
    @NotNull String[] getDes(FaCmdContext faCmdContext);
}