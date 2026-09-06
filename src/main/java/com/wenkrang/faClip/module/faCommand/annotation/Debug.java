package com.wenkrang.faClip.module.faCommand.annotation;

import java.lang.annotation.*;

/**
 * 调试用命令
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Debug {
}
