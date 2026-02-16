package com.wenkrang.faUtilities.Moudle.FaCommand.Checker;

import java.lang.reflect.Type;
import java.util.Set;

public class StringChecker implements ParamChecker{
    @Override
    public Set<Type> getType() {
        return Set.of(String.class);
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
