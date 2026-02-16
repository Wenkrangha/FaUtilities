package com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInterpreter;

import com.wenkrang.faUtilities.Helper.FaCmd.CmdNodeHelper;
import com.wenkrang.faUtilities.Moudle.FaCommand.Checker.FaChecker;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInstance;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * FaGuesser 类用于根据给定的参数猜测匹配的命令节点。
 * 它通过过滤和验证参数来确定哪些节点是有效的候选节点。
 */
public class FaGuesser {
    private final FaCmdInstance faCmdInstance;

    /**
     * 构造函数，初始化 FaGuesser 实例。
     *
     * @param faCmdInstance 命令实例对象，用于获取节点和方法信息
     */
    public FaGuesser(FaCmdInstance faCmdInstance) {
        this.faCmdInstance = faCmdInstance;
    }

    /**
     * 获取当前实例中的命令实例对象。
     *
     * @return 当前实例中的命令实例对象
     */
    public FaCmdInstance getFaCmdInstance() {
        return faCmdInstance;
    }

    public boolean ParamCheck(List<Set<Type>> expect, List<Type> got) {
        return IntStream.range(0, Math.min(got.size(), expect.size()))
                .allMatch(i -> expect.get(i).contains(got.get(i)));
    }
    

    /**
     * 根据给定的参数、节点和最大参数数，筛选出可能匹配的方法。
     *
     * @param args 实际参数列表，不能为空
     * @param node 命令节点字符串
     * @param max  截至参数数量限制
     * @return 符合条件的方法列表
     */
    public List<Method> getMethod(@NotNull ArrayList<String> args, String node, int max) {
        ArrayList<Method> probableMethods = new ArrayList<>();

        // 初始化参数类型检查器并获取参数的可能类型集合
        final FaChecker faChecker = new FaChecker();
        final List<Set<Type>> probableTypes = CmdNodeHelper.removeNode(node, args)
                .stream()
                .limit(max - node.split("\\.").length)
                .map(faChecker::check)
                .toList();

        // 获取指定节点下的所有方法
        final List<Method> methods = faCmdInstance.getFaCmd(node).getMethods();

        // 遍历所有方法，筛选出参数类型匹配的方法
        for (Method method : methods) {
            // 如果方法参数数量与输入参数数量不一致，则跳过
            Parameter[] parameters = method.getParameters();
            List<Type> methodTypes = Arrays.stream(parameters)
                    .map(i -> (Type) i.getType())
                    .filter(i -> !i.equals(FaCmdInterpreter.FaCmdContext.class))
                    .toList();

            if (methodTypes.size() != probableTypes.size()) continue;

            // 检查每个参数的类型是否匹配
            if (ParamCheck(probableTypes, methodTypes)) probableMethods.add(method);
        }

        return probableMethods;
    }


    /**
     * 根据参数和最大参数数筛选出最终的候选节点列表。
     *
     * @param args 实际参数列表
     * @param max  截至参数数量
     * @return 符合条件的节点列表
     */
    public ArrayList<String> guessNodes(@NotNull ArrayList<String> args, int max) {
        // 获取命中节点
        return new ArrayList<>(faCmdInstance
                .getNodes().stream()
                .filter(i -> CmdNodeHelper.formNode(args).startsWith(i))
                .filter(i -> !getMethod(args, i, max).isEmpty())
                .toList());
    }
}
