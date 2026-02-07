package com.wenkrang.faUtilities.Moudle.FaCommand;

import com.wenkrang.faUtilities.Helper.FaCmd.CmdHandleHelper;
import com.wenkrang.faUtilities.Helper.FaCmd.CmdNodeHelper;
import com.wenkrang.faUtilities.Moudle.FaCommand.AnnotationHandler.FaAnnotationHandler;
import com.wenkrang.faUtilities.Moudle.FaCommand.Checker.FaChecker;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
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







    public boolean interpret(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        try {
            //还原参数
            ArrayList<String> params = new ArrayList<>();
            params.add(commandLabel);
            params.addAll(List.of(args));

            //解析命令节点
            FaGuesser faGuesser = new FaGuesser(faCmdInstance);

            //猜测节点
            ArrayList<String> arrayArgs = new ArrayList<>(Arrays.asList(args));
            ArrayList<String> Nodes = (ArrayList<String>) faGuesser.guessNodes(arrayArgs, args.length);

            if (Nodes.size() > 1) {
                Logger.getGlobal().warning(t("FaCommand.Error.Interpreter.Conflict"));
                return false;
            }

            String node = Nodes.getFirst();

            //传递参数
            FaCmd faCmd = faCmdInstance.getFaCmd(node);
            Method method = faCmd.getMethods().stream()
                    .filter(i -> faGuesser.ParamCheck(node, i, params, args.length))
                    .findFirst().orElse(null);

            FaChecker faChecker = new FaChecker();
            ArrayList<String> realArgs = CmdNodeHelper.removeNode(node, params);

            if (method != null) {
                ArrayList<Object> convertedArgs = new ArrayList<>();

                for (int i = 0;i < method.getParameters().length;i++) {
                    convertedArgs.add(faChecker.parse(realArgs.get(i), method.getParameters()[i].getType()));
                }

                method.invoke(faCmd.getCommand(), convertedArgs.toArray());
            }

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
