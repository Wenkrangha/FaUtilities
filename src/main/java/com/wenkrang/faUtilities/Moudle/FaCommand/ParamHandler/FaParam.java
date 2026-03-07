package com.wenkrang.faUtilities.Moudle.FaCommand.ParamHandler;

import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmd;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faUtilities.Moudle.FaCommand.Helper.CmdNodeHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

public class FaParam {
    private final ArrayList<SimpleParam> simpleParams = new ArrayList<>();

    public FaParam() {
        simpleParams.add(new StringParam());
        simpleParams.add(new DoubleParam());
        simpleParams.add(new IntParam());
        simpleParams.add(new FloatParam());
        simpleParams.add(new LongParam());
    }

    /**
     * 检查给定的参数是否符合预定义的检查规则，并返回所有匹配的检查器类型。
     *
     * @param param 待检查的字符串参数
     * @return 包含所有匹配检查器类型的ArrayList，如果没有任何匹配则返回空列表
     */
    public @NotNull Set<Type> check(@NotNull String param) {
        // 初始化一个用于存储匹配检查器类型的列表
        ArrayList<Type> types = new ArrayList<>();

        // 遍历所有预定义的参数检查器
        for (SimpleParam paramChecker : simpleParams) {
            // 如果当前检查器匹配参数，则将其类型添加到结果列表中
            if (paramChecker.check(param)) {
                types.addAll(paramChecker.getType());
            }
        }

        // 返回包含所有匹配检查器类型的列表
        return new HashSet<>(types);
    }

    public @Nullable Object parse(@NotNull String param, @NotNull Type type) {
        for (SimpleParam paramChecker : simpleParams) {
            if (paramChecker.getType().contains(type)) {
                return paramChecker.convert(param);
            }
        }
        return null;
    }

    /**
     * 获取命令的用法
     *
     * @param cmd 命令
     * @return 命令的用法
     */
    public String @NotNull [] getUsage(@NotNull FaCmd cmd) {
        // 切割命令节点
        List<String> node = CmdNodeHelper.separateNode(cmd.getNode());

        // 获取命令的参数名称
        List<String> paramNames = Arrays.stream(cmd.getMethod().getParameters()) // 获取命令的方法参数
                .filter(i -> !i.getType().equals(FaCmdContext.class)) // 过滤掉 FaCmdContext 参数
                .map(i ->
                        simpleParams.stream()
                                .map(j -> j.getName(i.getType())) // 获取参数的名称
                                .filter(Objects::nonNull)
                                .map(j -> "<" + j + ">")
                                .toList()
                                .getFirst()
                ).toList();

        // 把节点和参数名称拼接起来
        return Stream.concat(node.stream(), paramNames.stream()).toArray(String[]::new);
    }
}
