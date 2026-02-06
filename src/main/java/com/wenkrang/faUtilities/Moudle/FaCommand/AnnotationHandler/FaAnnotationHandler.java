package com.wenkrang.faUtilities.Moudle.FaCommand.AnnotationHandler;

import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public interface FaAnnotationHandler {
    void handle(FaCmd command, Method method);
    Class<? extends Annotation> getAnnotationClass();
}
