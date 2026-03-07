package com.wenkrang.faUtilities.Moudle.FaCommand.ParamHandler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.Set;

public class LongParam implements SimpleParam {
    @Override
    public @NotNull Set<Type> getType() {
        return Set.of(Long.class, long.class); // 返回长整数对应的类型
    }

    @Override
    public boolean check(@NotNull String param) {
        try {
            // 尝试解析为 long
            Long.parseLong(param);
            return true;
        } catch (NumberFormatException e) {
            return false; // 无法解析为数字时返回 false
        }
    }

    @Override
    public @NotNull Object convert(@NotNull String param) {
        return Long.parseLong(param);
    }

    @Override
    public @Nullable String getName(Type type) {
        return getType().contains(type) ? "Long" : null;
    }
}
