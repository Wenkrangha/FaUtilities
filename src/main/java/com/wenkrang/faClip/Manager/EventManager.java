package com.wenkrang.faClip.Manager;

import com.wenkrang.faClip.Helper.ClassHelper;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;

public class EventManager {
    private final Plugin plugin;

    private final Map<String, Listener> listenerMap = new HashMap<>();


    public EventManager(Plugin p) {
        plugin = p;
    }

    /**
     * 自动注册监听器
     * @return 注册成功的监听器 ID
     */
    public List<String> autoRegister() {
        ArrayList<String> result = new ArrayList<>();

        // 扫描Class
        ArrayList<Class<?>> classes = ClassHelper.getClasses(plugin.getClass());

        for (Class<?> clazz : classes) {
            try {
                // 检查类是否为监听器
                if (Listener.class.isAssignableFrom(clazz)
                        && !clazz.isInterface()
                        && !Modifier.isAbstract(clazz.getModifiers())) {
                    // 获取构造器
                    Constructor<?>[] constructors = clazz.getDeclaredConstructors();

                    // 检查是否为无参构造器
                    // 自动注册只能注册无参构造类
                    boolean isValid = true;
                    for (Constructor<?> constructor : constructors) {
                        if (constructor.getParameterCount() != 0) {
                            isValid = false;
                            break;
                        }
                    }

                    if (isValid) {
                        try {
                            // 获取默认构造器
                            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
                            declaredConstructor.setAccessible(true);

                            // 构造监听器
                            Listener listener = (Listener) declaredConstructor.newInstance();

                            result.add(register(listener));
                        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                                 IllegalAccessException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 注册事件
     * @param listener 监听器
     * @return 监听器 ID
     */
    public String register(Listener listener) {
        // 生成监听器 ID
        String id = UUID.randomUUID().toString();

        listenerMap.put(id, listener);
        Bukkit.getPluginManager().registerEvents(listener, plugin);

        return id;
    }

    public void unregister(String id) {
        // 检查是否有该监听器
        if (!listenerMap.containsKey(id)) return;

        HandlerList.unregisterAll(listenerMap.get(id));
        listenerMap.remove(id);
    }

    public void unregisterAll() {
        for (String id : listenerMap.keySet()) {
            HandlerList.unregisterAll(listenerMap.get(id));
        }
        listenerMap.clear();
    }
}
