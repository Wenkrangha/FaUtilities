package com.wenkrang.faClip.module.faCommand.annotation;

import com.wenkrang.faClip.module.faCommand.interpreter.FaCmdContext;
import org.jetbrains.annotations.NotNull;

public interface DesProvider {
    @NotNull String[] getDes(FaCmdContext faCmdContext);
}