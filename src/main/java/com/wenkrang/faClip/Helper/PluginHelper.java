package com.wenkrang.faClip.Helper;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

public class PluginHelper {
    /**
     * 通过调用栈分析，自动获取调用方所属的插件实例
     * <p>原理：每个 Bukkit 插件有独立的 PluginClassLoader，
     * 通过 StackWalker 找到第一个非 FaClip 包的调用者类，
     * 再匹配其 ClassLoader 即可定位插件</p>
     * @return 调用方的插件实例，找不到时返回 null
     */
    @Nullable
    public static Plugin detectCallingPlugin() {
        Class<?> callerClass = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(frames -> frames
                        .map(StackWalker.StackFrame::getDeclaringClass)
                        .filter(c -> !c.getName().startsWith("com.wenkrang.faClip")
                                && !c.getName().startsWith("java.")
                                && !c.getName().startsWith("jdk."))
                        .findFirst()
                )
                .orElse(null);

        if (callerClass == null) return null;

        ClassLoader callerLoader = callerClass.getClassLoader();
        for (Plugin p : Bukkit.getPluginManager().getPlugins()) {
            if (p.getClass().getClassLoader() == callerLoader) {
                return p;
            }
        }
        return null;
    }
}
