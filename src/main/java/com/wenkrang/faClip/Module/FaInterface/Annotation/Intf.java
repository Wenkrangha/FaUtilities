package com.wenkrang.faClip.Module.FaInterface.Annotation;

import java.lang.annotation.*;

/**
 * FaClip的接口注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Intf {
    String value();
}
