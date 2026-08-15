package com.wenkrang.faClip.module.FaInterface;

import com.wenkrang.faClip.helper.ClassHelper;
import com.wenkrang.faClip.module.FaInterface.annotation.Intf;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * FaClip内部接口系统，运行各种模块访问注册的接口
 */
public class FaInterfaceInstance {
    private final Plugin plugin;

    private final ArrayList<FaIntf> faIntfs = new ArrayList<>();

    public FaIntfInterpreter getFaIntfInterpreter() {
        return faIntfInterpreter;
    }

    private final FaIntfInterpreter faIntfInterpreter;

    public FaInterfaceInstance(Plugin plugin) {
        this.plugin = plugin;
        faIntfInterpreter = new FaIntfInterpreter(this);
    }

    public FaIntf registerFaIntf(@NotNull Method method,@NotNull String node) {
        FaIntf intf = faIntfInterpreter.interpret(method, node);
        faIntfs.add(intf);
        return intf;
    }
    public void registerFaIntf(@NotNull Method method) {
        faIntfs.add(faIntfInterpreter.interpret(method));
    }
    public ArrayList<FaIntf> getFaIntfs() {
        return faIntfs;
    }

    public void enableForAll(Plugin plugin) {
        Class<? extends Plugin> aClass = plugin.getClass();

        ArrayList<Class<?>> classes = ClassHelper.getClasses(aClass);

        enableFor(classes.toArray(Class<?>[]::new));
    }

    public void enableFor(Class<?>[] classes) {
        for (Class<?> clazz : classes) {
            Method[] methods = clazz.getMethods();

            for (Method method : methods) {
                if (method.getAnnotation(Intf.class) != null) {
                    registerFaIntf(method);
                }
            }
        }
    }

    /**
     * 根据参数猜测接口
     * @param args 参数
     * @return 匹配的接口
     */
    public List<FaIntf> guessIntf(String[] args) {
        return faIntfs.stream().filter(i -> i.check(args) == FaIntfCheckResult.FULL_MATCH).toList();
    }

    /**
     * 根据节点获取接口
     * @param node 节点
     * @return 匹配的接口
     */
    public @NotNull List<FaIntf> getIntf(String node) {
        return faIntfs.stream().filter(i -> i.getNode().equalsIgnoreCase(node)).toList();
    }
}
