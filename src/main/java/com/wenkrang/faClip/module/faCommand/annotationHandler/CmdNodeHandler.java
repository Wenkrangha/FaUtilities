package com.wenkrang.faClip.module.faCommand.annotationHandler;

import com.wenkrang.faClip.module.faCommand.annotation.Cmd;
import com.wenkrang.faClip.module.faCommand.FaCmd;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Cmd 注解处理器
 * 负责处理 @Cmd 注解，提取命令节点路径并设置到 Builder 中
 */
public class CmdNodeHandler implements CmdAnnotationHandler {
    @Override
    public void handle(@NotNull FaCmd.Builder builder, @NotNull Method method) {
        Cmd cmd = method.getAnnotation(Cmd.class);
        String node = cmd.value();

        builder.node(node);
        builder.name(node.split("\\.")[node.split("\\.").length - 1]);
    }

    @Override
    public @NotNull Class<? extends Annotation> getAnnotationClass() {
        return Cmd.class;
    }
}
