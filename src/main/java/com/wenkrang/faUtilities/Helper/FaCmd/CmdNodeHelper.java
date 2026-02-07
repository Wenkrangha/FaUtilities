package com.wenkrang.faUtilities.Helper.FaCmd;

import com.wenkrang.faUtilities.Moudle.FaCommand.AnnotationHandler.CmdNodeHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class CmdNodeHelper {
    /**
     * 检查给定字符串是否符合指定的正则表达式规则。
     * 该方法用于验证字符串是否仅包含字母、数字和点号（"."）。
     *
     * @param s 待检查的字符串，不能为空（由@NotNull注解保证）
     * @return 如果字符串符合正则表达式规则则返回true，否则返回false
     */
       /**
     * 检查字符串是否只包含字母、数字和点号
     * @param s 待检查的字符串，不能为空
     * @return 如果字符串只包含字母、数字和点号则返回true，否则返回false
     */
    public static Boolean check(@NotNull String s) {
        String regex = "^[a-zA-Z0-9.]+$"; // 定义匹配规则：仅允许字母、数字和点号
        return s.matches(regex); // 使用正则表达式验证字符串是否匹配
    }

    /**
     * 将字符串按点号分割成字符串数组
     * @param s 待分割的字符串，不能为空
     * @return 分割后的字符串数组
     */
    public static String[] separateString(@NotNull String s) {
        return s.split("\\.");
    }

    /**
     * 获取命令字符串中的根命令（第一个点号前的部分）
     * @param s 命令字符串
     * @return 根命令部分
     */
    public static String getRootCommand(String s) {
        String[] strings = separateString(s);
        return strings[0];
    }

    /**
     * 获取命令字符串中的子命令（最后一个点号后的部分）
     * @param s 命令字符串
     * @return 子命令部分
     */
    public static String getSubCommand(String s) {
        String[] strings = separateString(s);
        return strings[strings.length - 1];
    }

    /**
     * 判断方法是否被CmdNode注解标记
     * @param method 待检查的方法
     * @return 如果方法被CmdNode注解标记则返回true，否则返回false
     */
    public static boolean isCmdNode(Method method) {
        return method.getAnnotation(CmdNodeHandler.CmdNode.class) != null;
    }

    /**
     * 将松散字符串转换为命令节点
     *
     * @param strings 待转换的松散字符串
     * @return 转换后的命令节点
     */
    public static String formNode(ArrayList<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        String node;

        for (String s : strings) {
            stringBuilder.append(s).append(".");
        }

        node = stringBuilder.substring(0, stringBuilder.length() - 1);

        return node;
    }


    public static ArrayList<String> removeNode(String node, ArrayList<String> args) {
        return (ArrayList<String>) args.stream().skip(separateNode(node).size()).toList();
    }

    /**
     * 分割命令节点
     *
     * @param node 待分割的命令节点
     * @return 分割后的命令节点数组
     */
    public static ArrayList<String> separateNode(String node) {
        String[] split = node.split("\\.");
        return new ArrayList<>(Arrays.asList(split));
    }
}
