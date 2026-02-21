package com.wenkrang.faUtilities.Moudle.FaCommand.ParamHandler;

import java.lang.reflect.Type;
import java.util.Set;

public interface ParamParam {
    Set<Type> getType();
    boolean check(String param);
    Object convert(String param);
}
