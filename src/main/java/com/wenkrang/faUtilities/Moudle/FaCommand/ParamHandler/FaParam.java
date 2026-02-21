package com.wenkrang.faUtilities.Moudle.FaCommand.ParamHandler;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FaParam {
    private final ArrayList<ParamParam> paramParams = new ArrayList<>();

    public FaParam() {
        paramParams.add(new StringParam());
        paramParams.add(new DoubleParam());
        paramParams.add(new IntParam());
        paramParams.add(new FloatParam());
        paramParams.add(new LongParam());
    }

    /**
     * 检查给定的参数是否符合预定义的检查规则，并返回所有匹配的检查器类型。
     *
     * @param param 待检查的字符串参数
     * @return 包含所有匹配检查器类型的ArrayList，如果没有任何匹配则返回空列表
     */
    public Set<Type> check(@NotNull String param) {
        // 初始化一个用于存储匹配检查器类型的列表
        ArrayList<Type> types = new ArrayList<>();

        // 遍历所有预定义的参数检查器
        for (ParamParam paramChecker : paramParams) {
            // 如果当前检查器匹配参数，则将其类型添加到结果列表中
            if (paramChecker.check(param)) {
                types.addAll(paramChecker.getType());
            }
        }

        // 返回包含所有匹配检查器类型的列表
        return new HashSet<>(types);
    }

    public Object parse(@NotNull String param,@NotNull Type type) {
        for (ParamParam paramChecker : paramParams) {
            if (paramChecker.getType().contains(type)) {
                return paramChecker.convert(param);
            }
        }
        return null;
    }

}
