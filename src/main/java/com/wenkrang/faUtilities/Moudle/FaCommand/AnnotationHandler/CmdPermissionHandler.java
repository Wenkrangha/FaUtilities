package com.wenkrang.faUtilities.Moudle.FaCommand.AnnotationHandler;

import com.wenkrang.faUtilities.Moudle.FaCommand.Annotation.CmdPermission;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CmdPermissionHandler implements FaAnnotationHandler{
    @Override
    public void handle(FaCmd command, Method method) {
        CmdPermission cmdPermission = method.getAnnotation(CmdPermission.class);
        command.setPermission(cmdPermission.value());
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return CmdPermission.class;
    }
}
