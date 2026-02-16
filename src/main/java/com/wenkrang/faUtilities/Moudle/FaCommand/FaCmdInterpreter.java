package com.wenkrang.faUtilities.Moudle.FaCommand;

import com.wenkrang.faUtilities.Helper.FaCmd.CmdHandleHelper;
import com.wenkrang.faUtilities.Helper.FaCmd.CmdNodeHelper;
import com.wenkrang.faUtilities.Moudle.FaCommand.AnnotationHandler.FaAnnotationHandler;
import com.wenkrang.faUtilities.Moudle.FaCommand.Checker.FaChecker;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.wenkrang.faUtilities.Helper.FaCmd.CmdHandleHelper.handleRootCommand;
import static com.wenkrang.faUtilities.Helper.i18nHelper.t;

public class FaCmdInterpreter {
    private final FaCmdInstance faCmdInstance;

    private final Plugin plugin;

    public FaCmdInterpreter(FaCmdInstance faCmdInstance, Plugin p) {
        this.faCmdInstance = faCmdInstance;
        this.plugin = p;
    }

    private ArrayList<FaAnnotationHandler> annotationHandlers = new ArrayList<>();

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
        new BukkitRunnable() {
            @Override
            public void run() {
                try {

                    // 还原完整的命令参数列表
                    ArrayList<String> params = new ArrayList<>();
                    params.add(commandLabel);
                    params.addAll(List.of(args));

                    // 创建命令节点解析器
                    FaGuesser faGuesser = new FaGuesser(faCmdInstance);

                    // 猜测可能的命令节点
                    ArrayList<String> Nodes = new ArrayList<>(faGuesser.guessNodes(params, params.size()));

                    // 检查是否存在命令冲突（多个匹配节点）
                    if (Nodes.size() > 1) {
                        Logger.getGlobal().warning(t("FaCommand.Error.Interpreter.Conflict"));
                    }

                    if (Nodes.isEmpty()) return;

                    String node = Nodes.getFirst();

                    // 获取对应的命令处理器和匹配的方法
                    FaCmd faCmd = faCmdInstance.getFaCmd(node);
                    Method method = faGuesser.getMethod(params, node, params.size()).getFirst();

                    // 准备参数转换器和实际参数
                    FaChecker faChecker = new FaChecker();
                    List<String> realArgs = CmdNodeHelper.removeNode(node, params);

                    // 执行匹配到的方法
                    if (method != null) {
                        ArrayList<Object> convertedArgs = new ArrayList<>();

                        // 转换参数类型
                        for (int i = 0;i < method.getParameters().length;i++) {
                            convertedArgs.add(faChecker.parse(realArgs.get(i), method.getParameters()[i].getType()));
                        }

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                try {
                                    method.invoke(this,convertedArgs.toArray());
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }.runTask(plugin);
                    }

                }catch (Exception e) {
                    e.printStackTrace();
//            Logger.getGlobal().warning(e.getMessage());
                }
            }
        }.runTaskAsynchronously(faCmdInstance.getPlugin());
        return true;
    }

    public FaCmdInstance getFaCmdInstance() {
        return faCmdInstance;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
