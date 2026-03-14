package com.wenkrang.faUtilities.Moudle.FaCommand.Annotation;

import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInterpreter.FaCmdContext;
import org.jetbrains.annotations.NotNull;

public interface DesProvider {
    @NotNull String[] getDes(FaCmdContext faCmdContext);
}