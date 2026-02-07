package com.wenkrang.faUtilities.Moudle.FaCommand.Checker;

import java.lang.reflect.Type;

public class StringChecker implements ParamChecker{
    @Override
    public Type getType() {
        return String.class;
    }

    @Override
    public boolean check(String param) {
        return true;
    }

    @Override
    public Object convert(String param) {
        return param;
    }
}
