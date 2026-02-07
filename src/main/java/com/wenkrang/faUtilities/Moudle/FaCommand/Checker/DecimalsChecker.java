package com.wenkrang.faUtilities.Moudle.FaCommand.Checker;

import java.lang.reflect.Type;

public class DecimalsChecker implements ParamChecker{
    @Override
    public Type getType() {
        return Double.class; // 返回小数对应的类型
    }

    @Override
    public boolean check(String param) {
        try {
            // 尝试解析为 double
            double value = Double.parseDouble(param);
            // 排除整数情况（即没有小数部分）
            return value != (int) value;
        } catch (NumberFormatException e) {
            return false; // 无法解析为数字时返回 false
        }
    }

    @Override
    public Object convert(String param) {
        return Double.parseDouble(param);
    }
}
