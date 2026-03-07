package com.wenkrang.faUtilities.Moudle.FaCommand.ParamHandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.Set;

public class StringParam implements SimpleParam {
    @Override
    public @NotNull Set<Type> getType() {
        return Set.of(String.class); // 返回字符串对应的类型
    }

    @Override
    public boolean check(String param) {
        return true;
    }

    @Override
    public Object convert(String param) {
        return param;
    }

    @Override
    public @Nullable String getName(Type type) {
        return getType().contains(type) ? "String" : null;
    }
}
