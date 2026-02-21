package com.wenkrang.faUtilities.Moudle.FaCommand.ParamHandler;

import java.lang.reflect.Type;
import java.util.Set;

public class LongParam implements ParamParam {
    @Override
    public Set<Type> getType() {
        return Set.of(Long.class, long.class); // 返回小数对应的类型
    }

    @Override
    public boolean check(String param) {
        try {
            // 尝试解析为 double
            double value = Long.parseLong(param);
            // 排除整数情况（即没有小数部分）
            return value != (int) value;
        } catch (NumberFormatException e) {
            return false; // 无法解析为数字时返回 false
        }
    }

    @Override
    public Object convert(String param) {
        return Long.parseLong(param);
    }
}
