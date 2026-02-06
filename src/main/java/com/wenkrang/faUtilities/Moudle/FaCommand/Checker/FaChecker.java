package com.wenkrang.faUtilities.Moudle.FaCommand.Checker;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FaChecker {
    private final ArrayList<ParamChecker> paramCheckers = new ArrayList<>();

    public FaChecker() {
        paramCheckers.add(new StringChecker());
    }

    /**
     * 检查给定的参数是否符合预定义的检查规则，并返回所有匹配的检查器类型。
     *
     * @param param 待检查的字符串参数
     * @return 包含所有匹配检查器类型的ArrayList，如果没有任何匹配则返回空列表
     */
    public ArrayList<Type> check(String param) {
        // 初始化一个用于存储匹配检查器类型的列表
        ArrayList<Type> types = new ArrayList<>();

        // 遍历所有预定义的参数检查器
        for (ParamChecker paramChecker : paramCheckers) {
            // 如果当前检查器匹配参数，则将其类型添加到结果列表中
            if (paramChecker.check(param)) {
                types.add(paramChecker.getClass());
            }
        }

        // 返回包含所有匹配检查器类型的列表
        return types;
    }

}
