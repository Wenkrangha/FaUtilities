package com.wenkrang.faClip.module.faCommand.annotationHandler;

import com.wenkrang.faClip.module.faCommand.annotation.Help;
import com.wenkrang.faClip.module.faCommand.FaCmd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CmdHelpHandler implements CmdAnnotationHandler {
    @Override
    public void handle(FaCmd.Builder builder, Method method) {
        String value = method.getAnnotation(Help.class).value();
        builder.help(value);
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return Help.class;
    }
}
