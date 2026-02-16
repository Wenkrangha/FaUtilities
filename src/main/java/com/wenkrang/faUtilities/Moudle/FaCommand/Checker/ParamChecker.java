package com.wenkrang.faUtilities.Moudle.FaCommand.Checker;

import java.lang.reflect.Type;
import java.util.Set;

public interface ParamChecker {
    Set<Type> getType();
    boolean check(String param);
    Object convert(String param);
}
