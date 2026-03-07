package com.wenkrang.faUtilities.Moudle.FaCommand.ParamHandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.Set;

public class IntParam implements SimpleParam {

    @Override
    public @NotNull Set<Type> getType() {
        return Set.of(Integer.class, int.class); // 返回整数对应的类型
    }

    @Override
    public boolean check(@NotNull String param) {
        try {
            Integer.parseInt(param);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public @NotNull Object convert(@NotNull String param) {
        return Integer.parseInt(param);
    }

    @Override
    public @Nullable String getName(Type type) {
        return getType().contains(type) ? "Integer" : null;
    }
}
