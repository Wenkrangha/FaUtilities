package com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInterpreter;

import com.wenkrang.faUtilities.Moudle.FaCommand.Checker.FaChecker;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FaParamHandler {
    public static ArrayList<String> getCompleteParam(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        // 还原完整的命令参数列表
        ArrayList<String> params = new ArrayList<>();
        params.add(commandLabel);
        params.addAll(List.of(args));

        return params;
    }

    public static List<Object> convertParams(
            @NotNull CommandSender sender
            , Method method, String[] args) {
        // 准备参数转换器和实际参数
        FaChecker faChecker = new FaChecker();

        ArrayList<Object> convertedArgs = new ArrayList<>();

        // 转换参数类型

        int argIndex = 0; // 添加参数索引计数器
        for (int i = 0; i < method.getParameters().length; i++) {
            if (method.getParameters()[i].getType().equals(FaCmdInterpreter.FaCmdContext.class)) {
                convertedArgs.add(new FaCmdInterpreter.FaCmdContext(sender, args));
                continue;
            }
            // 使用argIndex而不是i来访问realArgs
            convertedArgs.add(faChecker.parse(args[argIndex], method.getParameters()[i].getType()));
            argIndex++; // 只有非FaCmdHandle参数才增加索引
        }

        return convertedArgs;
    }
}
