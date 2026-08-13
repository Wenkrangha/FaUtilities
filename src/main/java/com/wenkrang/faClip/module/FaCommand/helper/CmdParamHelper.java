package com.wenkrang.faClip.module.FaCommand.helper;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CmdParamHelper {
    public static @NotNull ArrayList<String> getCompleteParam(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        // 还原完整的命令参数列表
        ArrayList<String> params = new ArrayList<>();
        params.add(commandLabel);
        params.addAll(List.of(args));

        return params;
    }

    /**
     * 将对象数组扁平化为字符串列表
     * @param objects 对象数组
     * @return 字符串列表
     */
    public static List<String> flat(Object[] objects) {
        ArrayList<String> result = new ArrayList<>();

        for (Object object : objects) {
            if (object instanceof String str) {
                result.add(str);
            } else if (object instanceof String[] strs) {
                result.addAll(List.of(strs));
            } else if (object instanceof ArrayList<?> strs) {
                result.addAll(strs.stream().map(String::valueOf).toList());
            }
        }

        return result;
    }
}
