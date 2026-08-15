package com.wenkrang.faClip.module.FaCommand.annotationHandler;

import com.wenkrang.faClip.module.FaCommand.annotation.CmdPermission;
import com.wenkrang.faClip.module.FaCommand.FaCmd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * CmdPermission 注解处理器
 * 负责处理 @CmdPermission 注解，提取权限节点并设置到 Builder 中
 */
public class CmdPermissionHandler implements CmdAnnotationHandler {
    @Override
    public void handle(FaCmd.Builder builder, Method method) {
        CmdPermission cmdPermission = method.getAnnotation(CmdPermission.class);
        builder.permission(cmdPermission.value());
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return CmdPermission.class;
    }
}
