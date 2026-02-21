package com.wenkrang.faUtilities.Moudle.FaCommand.AnnotationHandler;

import com.wenkrang.faUtilities.Moudle.FaCommand.Helper.CmdNodeHelper;
import com.wenkrang.faUtilities.Moudle.FaCommand.Annotation.CmdNode;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmd;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static com.wenkrang.faUtilities.Helper.i18nHelper.ft;
import static com.wenkrang.faUtilities.Helper.i18nHelper.t;
import static org.bukkit.Bukkit.getLogger;

public class CmdNodeHandler implements FaAnnotationHandler {
    @Override
    public void handle(FaCmd command, Method method) {
        CmdNode cmdNode = method.getAnnotation(CmdNode.class); // 获取命令节点

        if (!Modifier.isStatic(method.getModifiers()))
            throw new RuntimeException(ft("FaCommand.Error.Interpreter.NotStatic", method.getName()));

        String node = cmdNode.value(); // 获取挂载的命令节点

        // 检查命令节点是否合规
        if (CmdNodeHelper.check(node)) {
            //进行泛命令加载
            command.setNode(node);
            command.addMethod(method);
        }else {
            getLogger().warning(t("FaCommand.Error.Interpreter.CantUnderstand"));
        }
    }


    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return CmdNode.class;
    }


}
