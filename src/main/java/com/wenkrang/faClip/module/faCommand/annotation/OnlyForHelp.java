package com.wenkrang.faClip.module.faCommand.annotation;

import java.lang.annotation.*;

/**
 * 标记该节点只作为帮助提示承载体
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OnlyForHelp {
}
