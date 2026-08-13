package com.wenkrang.faClip.module.FaCommand.annotationHandler;

import com.wenkrang.faClip.module.FaCommand.annotation.OnlyForHelp;
import com.wenkrang.faClip.module.FaCommand.FaCmd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CmdOnlyForHelpHandler implements CmdAnnotationHandler {
    @Override
    public void handle(FaCmd command, Method method) {
        command.setOnlyForHelp(true);
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return OnlyForHelp.class;
    }
}
