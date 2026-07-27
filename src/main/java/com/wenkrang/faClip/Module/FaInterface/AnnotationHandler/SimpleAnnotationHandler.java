package com.wenkrang.faClip.Module.FaInterface.AnnotationHandler;

import com.wenkrang.faClip.Module.FaInterface.FaIntf;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public interface SimpleAnnotationHandler {
    void handle(FaIntf faIntf, Method method);
    Class<? extends Annotation> getAnnotationClass();
}
