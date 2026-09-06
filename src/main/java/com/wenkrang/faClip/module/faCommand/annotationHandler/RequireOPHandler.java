package com.wenkrang.faClip.module.faCommand.annotationHandler;

import com.wenkrang.faClip.module.faCommand.annotation.RequireOP;
import com.wenkrang.faClip.module.faCommand.FaCmd;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 处理 RequireOP 注解的处理器
 */
public class RequireOPHandler implements CmdAnnotationHandler {
    @Override
    public void handle(@NotNull FaCmd.Builder builder, @NotNull Method method) {
        builder.requireOP();
    }

    @Override
    public @NotNull Class<? extends Annotation> getAnnotationClass() {
        return RequireOP.class;
    }
}
