package com.wenkrang.faClip.Module.FaCommand.Helper;

import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.FaCmdContext;
import com.wenkrang.faClip.Module.FaInterface.FaParam.FaParam;
import com.wenkrang.faClip.Module.FaMessage.Fm;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.wenkrang.faClip.Module.FaMessage.Helper.i18nHelper.t;

public class CmdParamHelper {
    public static @NotNull ArrayList<String> getCompleteParam(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        // 还原完整的命令参数列表
        ArrayList<String> params = new ArrayList<>();
        params.add(commandLabel);
        params.addAll(List.of(args));

        return params;
    }

    public static @NotNull Object[] convertParams(
            @NotNull CommandSender sender
            , @NotNull Method method, String @NotNull [] args, @NotNull String node) {
        // 准备参数转换器和实际参数
        FaParam faChecker = new FaParam();

        String[] removedNodeArgs = NodeHelper.removeNode(node, Arrays.stream(args).toList()).toArray(String[]::new);

        // 转换参数类型
        Object[] convertedArgs = new Object[method.getParameterCount()];

        // 设置FaCmdContext
        IntStream.range(0, method.getParameterCount())
                .forEach(i -> {
                    if(method.getParameters()[i].getType().equals(FaCmdContext.class))
                        convertedArgs[i] = new FaCmdContext(sender, Arrays.stream(args).skip(1).toArray(String[]::new));
                });
        // 获取需要填充的参数位（空位置）
        List<Integer> nullPositions = IntStream.range(0, method.getParameterCount())
                .filter(i -> convertedArgs[i] == null)
                .boxed().toList();
        // 转换参数
        for (int i = 0;i < nullPositions.size(); i++) {
            if (i >= removedNodeArgs.length) {
                break;
            }
            Object parse = faChecker.parse(removedNodeArgs[i], method.getParameters()[nullPositions.get(i)].getType());
            if (parse == null) {
                Fm.waring(t("FaCommand.Error.Interpreter.ArgsNPEWarning"));
            }
            convertedArgs[nullPositions.get(i)] = parse;
        }


//        int argIndex = 0; // 添加参数索引计数器
//        for (int i = 0; i < method.getParameters().length; i++) {
//            if (method.getParameters()[i].getType().equals(FaCmdContext.class)) {
//                convertedArgs.add(new FaCmdContext(sender, Arrays.stream(args).skip(1).toArray(String[]::new)));
//                continue;
//            }
//            // 使用argIndex而不是i来访问realArgs
//            convertedArgs.add(faChecker.parse(removedNodeArgs[argIndex], method.getParameters()[i].getType()));
//            argIndex++; // 只有非FaCmdHandle参数才增加索引
//        }

        return convertedArgs;
    }
}
