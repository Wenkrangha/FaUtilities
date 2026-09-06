package com.wenkrang.faClip.module.faCommand.annotationHandler;

import com.wenkrang.faClip.module.faCommand.FaCmd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 注解处理器接口
 * <p>用于处理命令方法上的各种注解，提取配置信息并设置到 Builder 中。</p>
 */
public interface CmdAnnotationHandler {
    /**
     * 处理注解，将注解信息提取并设置到 Builder 中
     *
     * @param builder 命令构建器
     * @param method 带有注解的方法
     */
    void handle(FaCmd.Builder builder, Method method);

    /**
     * 获取此处理器处理的注解类型
     *
     * @return 注解类对象
     */
    Class<? extends Annotation> getAnnotationClass();
}
