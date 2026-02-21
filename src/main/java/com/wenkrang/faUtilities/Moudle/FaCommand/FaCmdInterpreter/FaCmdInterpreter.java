package com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInterpreter;

import com.wenkrang.faUtilities.Moudle.FaCommand.ParamHandler.FaParam;
import com.wenkrang.faUtilities.Moudle.FaCommand.Helper.CmdHandleHelper;
import com.wenkrang.faUtilities.Moudle.FaCommand.Helper.CmdNodeHelper;
import com.wenkrang.faUtilities.Moudle.FaCommand.AnnotationHandler.FaAnnotationHandler;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmd;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInstance;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.wenkrang.faUtilities.Moudle.FaCommand.Helper.CmdHandleHelper.handleRootCommand;
import static com.wenkrang.faUtilities.Helper.i18nHelper.t;

public class FaCmdInterpreter {
    private final FaCmdInstance faCmdInstance;

    private final Plugin plugin;

    public FaCmdInterpreter(FaCmdInstance faCmdInstance, Plugin p) {
        this.faCmdInstance = faCmdInstance;
        this.plugin = p;
    }

    private ArrayList<FaAnnotationHandler> annotationHandlers = new ArrayList<>();

    public static ArrayList<String> getCompleteParam(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        // 还原完整的命令参数列表
        ArrayList<String> params = new ArrayList<>();
        params.add(commandLabel);
        params.addAll(List.of(args));

        return params;
    }

    public static List<Object> convertParams(
            @NotNull CommandSender sender
            , Method method, String[] args, String node) {
        // 准备参数转换器和实际参数
        FaParam faChecker = new FaParam();

        ArrayList<Object> convertedArgs = new ArrayList<>();


        String[] removedNodeArgs = CmdNodeHelper.removeNode(node, Arrays.stream(args).toList()).toArray(String[]::new);
        // 转换参数类型

        int argIndex = 0; // 添加参数索引计数器
        for (int i = 0; i < method.getParameters().length; i++) {
            if (method.getParameters()[i].getType().equals(FaCmdContext.class)) {
                convertedArgs.add(new FaCmdContext(sender, Arrays.stream(args).skip(1).toArray(String[]::new)));
                continue;
            }
            // 使用argIndex而不是i来访问realArgs
            convertedArgs.add(faChecker.parse(removedNodeArgs[argIndex], method.getParameters()[i].getType()));
            argIndex++; // 只有非FaCmdHandle参数才增加索引
        }

        return convertedArgs;
    }

    public void addAnnotationHandlers(FaAnnotationHandler annotationHandler) {
        this.annotationHandlers.add(annotationHandler);
    }

    public void setAnnotationHandlers(ArrayList<FaAnnotationHandler> annotationHandlers) {
        this.annotationHandlers = annotationHandlers;
    }

    public ArrayList<FaAnnotationHandler> getAnnotationHandlers() {
        return annotationHandlers;
    }

    public void initialize(@NotNull Method method) {
        if (!CmdNodeHelper.isCmdNode(method)) return;

        FaCmd faCmd = new FaCmd(this);

        annotationHandlers.stream()
                .filter(i -> method.isAnnotationPresent(i.getAnnotationClass()))
                .forEach(i -> i.handle(faCmd, method));

        register(faCmd);
    }

    public void register(@NotNull FaCmd faCmd) {
        //检查命令节点是否设置
        if (faCmd.getNode() != null){
            //检查根命令是否注册
            String rootCommand = CmdNodeHelper.getRootCommand(faCmd.getNode());

            if (CmdHandleHelper.isUnregistered(rootCommand)) {
                handleRootCommand(rootCommand, faCmd);
            }

            faCmdInstance.addFaCmd(faCmd);
        }
    }

    /**
     * 解析并执行命令
     * 
     * @param sender 命令发送者，不能为空
     * @param commandLabel 命令标签，不能为空
     * @param args 命令参数数组，不能为空
     * @return 执行成功返回true，失败返回false
     */
    public boolean interpret(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        // 命令执行器的args，由于Spigot没传根命令，需要自己加一下commandLabel

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    // 获取完整参数列表
                    ArrayList<String> params = getCompleteParam(sender,commandLabel,args);

                    // 创建命令节点解析器
                    FaGuesser faGuesser = new FaGuesser(faCmdInstance);

                    // 猜测可能的命令节点
//                    ArrayList<String> Nodes = new ArrayList<>(faGuesser.guessNodes(params, params.size()));
//
//                    // 检查是否存在命令冲突（多个匹配节点）
//                    if (Nodes.size() > 1) {
//                        Logger.getGlobal().warning(t("FaCommand.Error.Interpreter.Conflict"));
//                    }
//
//                    if (Nodes.isEmpty()) return;
//
//                    String node = Nodes.getFirst();


                }catch (Exception e) {
                    Logger.getGlobal().warning(e.getMessage());
                }
            }
        }.runTaskAsynchronously(faCmdInstance.getPlugin());
        return true;
    }

    public boolean removeItemsWithTooFewParameters(String node,Method method,String[] args) {
        return Arrays.stream(method.getParameters())
                .filter(i -> i.getType() != FaCmdContext.class).count()
                + CmdNodeHelper.separateNode(node).size()
                <
                args.length;
    }

    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        // 这里的args就是完整的参数，包括""（没输入但写了空格）
        // Spigot是直接split了空格然后传过来

        return null;
    }

    public FaCmdInstance getFaCmdInstance() {
        return faCmdInstance;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
