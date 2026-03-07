package com.wenkrang.faUtilities.Moudle.FaCommand.ParamHandler;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.Set;

public interface SimpleParam {
    Set<Type> getType();
    boolean check(String param);
    Object convert(String param);
    @Nullable String getName(Type type);
}
