package com.wenkrang.faClip.module.faCommand.annotation;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Help {
    @NotNull String value();
}
