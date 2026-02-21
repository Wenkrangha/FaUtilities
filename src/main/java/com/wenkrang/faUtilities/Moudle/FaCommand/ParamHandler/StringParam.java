package com.wenkrang.faUtilities.Moudle.FaCommand.ParamHandler;

import java.lang.reflect.Type;
import java.util.Set;

public class StringParam implements ParamParam {
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
