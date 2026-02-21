package com.wenkrang.faUtilities.Moudle.FaCommand.ParamHandler;

import java.lang.reflect.Type;
import java.util.Set;

public class IntParam implements ParamParam {

    @Override
    public Set<Type> getType() {
        return Set.of(Integer.class, int.class);
    }

    @Override
    public boolean check(String param) {
        try {
            Integer.parseInt(param);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Object convert(String param) {
        return Integer.parseInt(param);
    }
}
