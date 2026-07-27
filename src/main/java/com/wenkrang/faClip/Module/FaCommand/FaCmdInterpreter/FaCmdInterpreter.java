package com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter;

import com.wenkrang.faClip.FaClip;
import com.wenkrang.faClip.Module.FaCommand.Annotation.Debug;
import com.wenkrang.faClip.Module.FaCommand.AnnotationHandler.CmdAnnotationHandler;
import com.wenkrang.faClip.Module.FaCommand.FaCmd;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInstance;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.SimpleStage;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.interpreter.conflictCheckStage;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.interpreter.emptyCheckStage;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.interpreter.onlyForHelpStage;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.interpreter.opCheckStage;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.interpreter.permissionCheckStage;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.interpreter.playerCheckStage;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.tabComplete.tabOpCheckStage;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.tabComplete.tabPermissionCheckStage;
import com.wenkrang.faClip.Module.FaCommand.FaCmdInterpreter.stage.tabComplete.tabPlayerCheckStage;
import com.wenkrang.faClip.Module.FaCommand.FaHelperGenerator.FaHelperGenerator;
import com.wenkrang.faClip.Module.FaCommand.Helper.CmdParamHelper;
import com.wenkrang.faClip.Module.FaInterface.FaIntf;
import com.wenkrang.faClip.Module.FaInterface.FaParam.FaParam;
import com.wenkrang.faClip.Module.FaCommand.Helper.CmdHandleHelper;
import com.wenkrang.faClip.Module.FaCommand.Helper.NodeHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.*;

import static com.wenkrang.faClip.Module.FaCommand.Helper.CmdHandleHelper.handleRootCommand;

public class FaCmdInterpreter {
    // 解释器管线
    private final ArrayList<SimpleStage> interpreterPipe = new ArrayList<>();

    // 补全管线
    private final ArrayList<SimpleStage> tabPipe = new ArrayList<>();

    private final FaCmdInstance faCmdInstance;

    private final Plugin plugin;

    public FaCmdInterpreter(FaCmdInstance faCmdInstance, Plugin p) {
        this.faCmdInstance = faCmdInstance;
        this.plugin = p;
        initPipes();
    }

    public void addInterpreterStage(SimpleStage stage) {
        interpreterPipe.add(stage);
    }
    
    public void addTabCompleteStage(SimpleStage stage) {
        tabPipe.add(stage);
    }
    
    /**
     * 初始化解释器管线和补全管线，注册所有检查阶段
     */
    private void initPipes() {
        // 解释器管线：空检查 -> 冲突检查 -> OP检查 -> 权限检查 -> 玩家检查 -> 仅帮助检查
        addInterpreterStage(new emptyCheckStage());
        addInterpreterStage(new conflictCheckStage());
        addInterpreterStage(new opCheckStage());
        addInterpreterStage(new permissionCheckStage());
        addInterpreterStage(new playerCheckStage());
        addInterpreterStage(new onlyForHelpStage());

        // 补全管线：OP检查 -> 权限检查 -> 玩家检查
        addTabCompleteStage(new tabOpCheckStage());
        addTabCompleteStage(new tabPermissionCheckStage());
        addTabCompleteStage(new tabPlayerCheckStage());
    }

    private ArrayList<CmdAnnotationHandler> annotationHandlers = new ArrayList<>();

    public void addAnnotationHandlers(CmdAnnotationHandler annotationHandler) {
        this.annotationHandlers.add(annotationHandler);
    }

    public void setAnnotationHandlers(ArrayList<CmdAnnotationHandler> annotationHandlers) {
        this.annotationHandlers = annotationHandlers;
    }

    public ArrayList<CmdAnnotationHandler> getAnnotationHandlers() {
        return annotationHandlers;
    }

    public void initialize(@NotNull Method method) {
        if (!NodeHelper.isCmdNode(method)) return;

        FaCmd faCmd = new FaCmd(this);

        faCmd.setPlugin(faCmdInstance.getPlugin());
        faCmd.setFaCmdInstance(faCmdInstance);

        annotationHandlers.stream()
                .filter(i -> method.isAnnotationPresent(i.getAnnotationClass()))
                .forEach(i -> i.handle(faCmd, method));

        // 初始化接口
        FaIntf faIntf = getFaCmdInstance().faInterfaceInstance.registerFaIntf(method, faCmd.getNode());

        faCmd.setFaIntf(faIntf);

        // 如果没启用调试模式，就不启用调试命令
        if (method.getAnnotation(Debug.class) != null && !FaClip.debug) return;

        register(faCmd);
    }

    public void register(@NotNull FaCmd faCmd) {
        //检查命令节点是否设置
        if (faCmd.getNode() != null){
            //检查根命令是否注册
            String rootCommand = NodeHelper.getRoot(faCmd.getNode());

            if (CmdHandleHelper.isUnregistered(rootCommand)) {
                handleRootCommand(rootCommand, faCmd, this);
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
        // 构建完整参数列表（Spigot未传根命令，需自行拼接）
        ArrayList<String> cArgs = CmdParamHelper.getCompleteParam(sender, commandLabel, args);

        try {
            ArrayList<String> params = CmdParamHelper.getCompleteParam(sender, commandLabel, args);
            List<FaIntf> faIntfs = faCmdInstance.faInterfaceInstance.guessIntf(params.toArray(String[]::new));
            List<FaCmd> faCmds = faIntfs.stream().map(faCmdInstance::getFaCmd).toList();

            FaCmdContext context = new FaCmdContext(sender, args);

            // 遍历解释器管线，任意阶段返回false则终止执行
            for (SimpleStage stage : interpreterPipe) {
                FaCmd cmdForStage = faCmds.isEmpty() ? null : faCmds.getFirst();
                if (!stage.check(cmdForStage, context, faCmds)) {
                    return false;
                }
            }

            FaCmd faCmd = faCmds.stream().findFirst().orElse(null);

            try {
                // 执行方法
                Object invoke = faCmd.getFaIntf().invoke(null, context, cArgs.toArray(String[]::new));
                if (invoke instanceof Boolean) {
                    return (Boolean) invoke;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        // Spigot直接split空格后传参
        ArrayList<String> cArgs = CmdParamHelper.getCompleteParam(sender, commandLabel, args);

        // 跳过根命令为空的情况
        if (cArgs.isEmpty()) {return null;}

        // 获取命令，使用模糊模式
        List<FaCmd> faCmds = faCmdInstance
                .faInterfaceInstance
                .getFaIntfs()
                .stream()
                .filter(i -> i.fuzzyCheck(cArgs.toArray(String[]::new)))
                .map(faCmdInstance::getFaCmd).toList();

        if (faCmds.isEmpty()) return new ArrayList<>();

        FaCmdContext context = new FaCmdContext(sender, args);

        // 通过补全管线过滤命令
        List<FaCmd> filteredCmds = new ArrayList<>();
        for (FaCmd cmd : faCmds) {
            boolean passed = true;
            for (SimpleStage stage : tabPipe) {
                if (!stage.check(cmd, context, faCmds)) {
                    passed = false;
                    break;
                }
            }
            if (passed) filteredCmds.add(cmd);
        }

        if (filteredCmds.isEmpty()) return new ArrayList<>();

        // 获取所有通过过滤的命令的用法
        FaParam faParam = new FaParam();
        FaHelperGenerator faHelperGenerator = new FaHelperGenerator(faCmdInstance);

        Object[] usages = filteredCmds.stream()
                .map(i -> faHelperGenerator.getUsage(i.getFaIntf(), context, true))
                .filter(i -> i.length >= cArgs.size())
                .map(i -> i[cArgs.size() - 1])
                .filter(Objects::nonNull)
                .toArray();

        return faParam.flat(usages);
    }

    public FaCmdInstance getFaCmdInstance() {
        return faCmdInstance;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
