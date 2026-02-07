package com.wenkrang.faUtilities.Moudle.FaCommand.Checker;

import java.lang.reflect.Type;

public interface ParamChecker {
    Type getType();
    boolean check(String param);
    Object convert(String param);
}
