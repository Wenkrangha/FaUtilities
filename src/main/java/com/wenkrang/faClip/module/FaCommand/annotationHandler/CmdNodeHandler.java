package com.wenkrang.faClip.module.FaCommand.annotationHandler;

import com.wenkrang.faClip.module.FaCommand.annotation.Cmd;
import com.wenkrang.faClip.module.FaCommand.FaCmd;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * CmdNode 注解处理器
 * 负责处理 @CmdNode 注解，提取命令节点路径并设置到 FaCmd 对象中
 */
public class CmdNodeHandler implements CmdAnnotationHandler {
    @Override
    public void handle(@NotNull FaCmd command, @NotNull Method method) {
        Cmd cmd = method.getAnnotation(Cmd.class); // 获取命令节点

        String node = cmd.value(); // 获取挂载的命令节点

        //进行泛命令加载
        command.setNode(node);
        command.setName(node.split("\\.")[node.split("\\.").length - 1]);
    }


    @Override
    public @NotNull Class<? extends Annotation> getAnnotationClass() {
        return Cmd.class;
    }


}
