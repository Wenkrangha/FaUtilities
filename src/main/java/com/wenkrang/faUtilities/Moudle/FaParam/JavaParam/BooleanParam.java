package com.wenkrang.faUtilities.Moudle.FaParam.JavaParam;

import com.wenkrang.faUtilities.Moudle.FaParam.SimpleParam;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * BooleanParam 用于处理布尔类型的参数。
 * 支持 true/false 的不区分大小写输入。
 */
public class BooleanParam implements SimpleParam {
    @Override
    public @NotNull Set<Type> getType() {
        return Set.of(Boolean.class, boolean.class);
    }

    @Override
    public boolean check(@NotNull String param) {
        String lowerParam = param.toLowerCase();
        return "true".equals(lowerParam) || "false".equals(lowerParam);
    }

    @Override
    public @NotNull Object convert(@NotNull String param) {
        return Boolean.parseBoolean(param);
    }

    @Override
    public @Nullable String getName(Type type) {
        return getType().contains(type) ? "Boolean" : null;
    }
}
