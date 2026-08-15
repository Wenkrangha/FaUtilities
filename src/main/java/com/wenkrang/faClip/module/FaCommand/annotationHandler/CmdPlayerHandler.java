package com.wenkrang.faClip.module.FaCommand.annotationHandler;

import com.wenkrang.faClip.module.FaCommand.annotation.ForPlayer;
import com.wenkrang.faClip.module.FaCommand.FaCmd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CmdPlayerHandler implements CmdAnnotationHandler {
    @Override
    public void handle(FaCmd.Builder builder, Method method) {
        builder.forPlayer();
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return ForPlayer.class;
    }
}
