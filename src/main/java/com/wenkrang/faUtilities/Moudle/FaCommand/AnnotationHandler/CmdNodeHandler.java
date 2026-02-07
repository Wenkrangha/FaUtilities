package com.wenkrang.faUtilities.Moudle.FaCommand.AnnotationHandler;

import com.wenkrang.faUtilities.Helper.FaCmd.CmdHandleHelper;
import com.wenkrang.faUtilities.Helper.FaCmd.CmdNodeHelper;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmd;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;
import java.lang.reflect.Method;

import static com.wenkrang.faUtilities.Helper.i18nHelper.t;
import static org.bukkit.Bukkit.getLogger;

public class CmdNodeHandler implements FaAnnotationHandler {
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    public @interface CmdNode {
        @NotNull String value();
    }

    @Override
    public void handle(FaCmd command, Method method) {
        CmdNode cmdNode = method.getAnnotation(CmdNode.class); // 获取命令节点

        String node = cmdNode.value(); // 获取挂载的命令节点

        // 检查命令节点是否合规
        if (CmdNodeHelper.check(node)) {
            // 获得根命令
            String rootCommand = CmdNodeHelper.getRootCommand(node);

            CmdHandleHelper.handleRootCommand(rootCommand,command);

            //进行泛命令加载
            command.setNode(node);
        }else {
            getLogger().warning(t("FaCommand.Error.Interpreter.CantUnderstand"));
        }
    }


    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return CmdNode.class;
    }


}
