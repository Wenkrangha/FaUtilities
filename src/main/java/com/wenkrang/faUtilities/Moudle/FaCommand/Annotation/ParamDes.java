package com.wenkrang.faUtilities.Moudle.FaCommand.Annotation;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ParamDes {
     @NotNull String value();
}
