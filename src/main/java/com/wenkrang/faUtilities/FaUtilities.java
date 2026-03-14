package com.wenkrang.faUtilities;

import com.wenkrang.faUtilities.Moudle.FaCommand.Annotation.Cmd;
import com.wenkrang.faUtilities.Moudle.FaCommand.Annotation.ParamDes;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInstance;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInterpreter.FaCmdContext;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * FaUtilities 主插件类
 * 负责插件的启动、关闭以及命令注册
 */
public final class FaUtilities extends JavaPlugin {

    /** 命令实例管理器 */
    FaCmdInstance faCmdInstance;

    /**
     * 插件启用时调用
     * 初始化命令实例并注册所有命令
     */
    @Override
    public void onEnable() {
        // Plugin startup logic
        faCmdInstance = FaCmdInstance.create(this);
        faCmdInstance.enableForAll(this);
    }

    /**
     * 插件禁用时调用
     * 清理并注销所有注册的命令
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        faCmdInstance.close();
    }


    @Cmd("fautilities.HelloWorld")
    public static void helloWorld(FaCmdContext faCmdContext
                                        ,@ParamDes("name") String name) {
        System.out.println("Hello World!");
        System.out.println(faCmdContext.sender().getName());
        System.out.println(name);
    }

    @Cmd("fautilities.HelloWorld2")
    public static void helloWorld(FaCmdContext faCmdContext,String name,boolean bool) {
        System.out.println("Hello World!");
        System.out.println(faCmdContext.sender().getName());
        System.out.println(name);
        System.out.println(bool);
    }


}
