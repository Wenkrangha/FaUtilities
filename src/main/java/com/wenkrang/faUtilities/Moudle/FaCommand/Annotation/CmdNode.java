package com.wenkrang.faUtilities.Moudle.FaCommand.Annotation;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CmdNode {
    @NotNull String value();
}
