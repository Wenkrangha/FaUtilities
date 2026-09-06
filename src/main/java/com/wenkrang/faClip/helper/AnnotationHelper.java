package com.wenkrang.faClip.helper;

import org.bukkit.plugin.Plugin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Constructor;

/**
 * AnnotationHelper
 * 用于获取注解相关类、方法、字段、构造器的工具类
 */
public class AnnotationHelper {
    private final Plugin plugin;

    public AnnotationHelper(Plugin plugin) {
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public List<Class<?>> getClasses(Annotation annotation) {
        ArrayList<Class<?>> results = new ArrayList<>();

        ArrayList<Class<?>> classes = ClassHelper.getClasses(plugin.getClass());

        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(annotation.getClass())) results.add(clazz);
        }

        return results;
    }

    public List<Method> getMethods(Annotation annotation) {
        ArrayList<Method> results = new ArrayList<>();

        ArrayList<Class<?>> classes = ClassHelper.getClasses(plugin.getClass());

        for (Class<?> clazz : classes) {
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(annotation.getClass())) results.add(method);
            }
        }

        return results;
    }
    public List<Field> getFields(Annotation annotation) {
        ArrayList<Field> results = new ArrayList<>();

        ArrayList<Class<?>> classes = ClassHelper.getClasses(plugin.getClass());

        for (Class<?> clazz : classes) {
            for (Field field : clazz.getFields()) {
                if (field.isAnnotationPresent(annotation.getClass())) results.add(field);
            }
        }

        return results;
    }
    public List<Constructor<?>> getConstructors(Annotation annotation) {
        ArrayList<Constructor<?>> results = new ArrayList<>();

        ArrayList<Class<?>> classes = ClassHelper.getClasses(plugin.getClass());

        for (Class<?> clazz : classes) {
            for (Constructor<?> constructor : clazz.getConstructors()) {
                if (constructor.isAnnotationPresent(annotation.getClass())) results.add(constructor);
            }
        }

        return results;
    }
}
