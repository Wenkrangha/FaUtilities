package com.wenkrang.faUtilities.Moudle.FaCommand;

import com.wenkrang.faUtilities.Helper.FaCmd.CmdHandleHelper;
import com.wenkrang.faUtilities.Helper.FaCmd.CmdNodeHelper;
import com.wenkrang.faUtilities.Moudle.FaCommand.AnnotationHandler.FaAnnotationHandler;
import com.wenkrang.faUtilities.Moudle.FaCommand.Checker.FaChecker;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.wenkrang.faUtilities.Helper.FaCmd.CmdHandleHelper.handleRootCommand;

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

        FaCmd faCmd = new FaCmd();

        annotationHandlers.stream()
                .filter(i -> method.isAnnotationPresent(i.getAnnotationClass()))
                .forEach(i ->
                        i.handle(faCmd, method));

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
     * 检查节点的参数是否少于实际所给参数
     * 一般来说，如果命令节点的参数少于实际所给参数，那么该节点不是所需节点
     *
     * @param args 实际参数
     * @param node 节点
     * @return 节点是否是所需节点
     */
    public boolean isInsufficientParameters(@NotNull ArrayList<String> args, String node) {
        return faCmdInstance.getFaCmd(node).getMethods()
                .stream().anyMatch(i -> i.getParameters().length < args.size() - 1);
    }

    public ArrayList<String> basicGuessNodes(@NotNull ArrayList<String> args) {
        return (ArrayList<String>) faCmdInstance.getNodes().stream()
                .filter(i -> args.getFirst().equals(CmdNodeHelper.getRootCommand(i)))
                .filter(i -> !isInsufficientParameters(args, i)).toList();
    }

    public boolean ParamCheck(Method method, @NotNull ArrayList<String> args) {
        List<ArrayList<Type>> propertyTypes = args.stream().skip(1).map(i -> new FaChecker().check(i)).toList();
        Parameter[] parameters = method.getParameters();



        return false;
    }

    public String[] guessNodes(@NotNull ArrayList<String> args) {
        basicGuessNodes(args)
                .stream().filter(i -> CmdNodeHelper.formNode(args).startsWith(i));


        return null;
    }

    public boolean interpret(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        try {
            //还原参数
            ArrayList<String> params = new ArrayList<>();
            params.add(commandLabel);
            params.addAll(List.of(args));

            //解析命令节点
            String[] Nodes = guessNodes(params);


            return true;
        }catch (Exception e) {
            Logger.getGlobal().warning(e.getMessage());
            return false;
        }
    }

    public FaCmdInstance getFaCmdInstance() {
        return faCmdInstance;
    }
}
