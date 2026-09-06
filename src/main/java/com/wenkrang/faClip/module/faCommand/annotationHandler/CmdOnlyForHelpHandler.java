package com.wenkrang.faClip.module.faCommand.annotationHandler;

import com.wenkrang.faClip.module.faCommand.annotation.OnlyForHelp;
import com.wenkrang.faClip.module.faCommand.FaCmd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CmdOnlyForHelpHandler implements CmdAnnotationHandler {
    @Override
    public void handle(FaCmd.Builder builder, Method method) {
        builder.onlyForHelp();
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return OnlyForHelp.class;
    }
}
