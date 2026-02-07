package com.wenkrang.faUtilities.Moudle.FaCommand;

import com.wenkrang.faUtilities.Helper.FaCmd.CmdNodeHelper;
import com.wenkrang.faUtilities.Moudle.FaCommand.Checker.FaChecker;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * 根据根命令筛选出可能的节点列表。
     *
     * @param args 参数列表，第一个元素作为根命令进行匹配
     * @return 匹配到的节点列表
     */
    public ArrayList<String> basicGuessNodes(@NotNull ArrayList<String> args) {
        return (ArrayList<String>) faCmdInstance.getNodes().stream()
                .filter(i -> args.getFirst().equals(CmdNodeHelper.getRootCommand(i)))
                .filter(i -> !isInsufficientParameters(args, i)).toList();
    }

    /**
     * 检查给定的方法参数是否与实际参数类型匹配。
     *
     * @param node  节点名称
     * @param method 方法对象
     * @param args  实际参数列表
     * @param max   截至参数数量
     * @return 如果所有参数类型都匹配则返回 true，否则返回 false
     */
    public boolean ParamCheck(String node, Method method, @NotNull ArrayList<String> args, int max) {
        FaChecker faChecker = new FaChecker();
        ArrayList<String> strings = CmdNodeHelper.removeNode(node, args);
        List<ArrayList<Type>> propertyTypes = strings.stream().map(faChecker::check).toList();
        Parameter[] parameters = method.getParameters();

        int limit = Math.min(max, args.size());

        if (args.size() == limit) {
            for (int i = 0; i < limit - CmdNodeHelper.separateNode(node).size(); i++) {
                if (!propertyTypes.get(i).contains(parameters[i].getType())) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    /**
     * 检查是否存在至少一个方法满足参数匹配条件。
     *
     * @param faCmd 命令对象
     * @param args  实际参数列表
     * @param max   截至参数数量
     * @return 如果存在匹配的方法则返回 true，否则返回 false
     */
    public boolean CmdParamAnyMatch(FaCmd faCmd, @NotNull ArrayList<String> args, int max) {
        return faCmd.getMethods().stream().anyMatch(i -> ParamCheck(faCmd.getNode(), i, args, max));
    }

    /**
     * 根据参数和最大参数数筛选出最终的候选节点列表。
     *
     * @param args 实际参数列表
     * @param max  截至参数数量
     * @return 符合条件的节点列表
     */
    public List<String> guessNodes(@NotNull ArrayList<String> args, int max) {
        return basicGuessNodes(args)
                .stream().filter(i -> CmdNodeHelper.formNode(args).startsWith(i))
                .filter(i -> CmdParamAnyMatch(faCmdInstance.getFaCmd(i), args, max)).toList();
    }

    /**
     * 检查节点的参数是否少于实际所给参数。
     * 一般来说，如果命令节点的参数少于实际所给参数，那么该节点不是所需节点。
     *
     * @param args 实际参数
     * @param node 节点
     * @return 节点是否是所需节点
     */
    public boolean isInsufficientParameters(@NotNull ArrayList<String> args, String node) {
        return faCmdInstance.getFaCmd(node).getMethods()
                .stream().anyMatch(i -> i.getParameters().length < args.size() - 1);
    }
}
