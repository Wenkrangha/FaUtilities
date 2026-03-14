package com.wenkrang.faUtilities;

import com.wenkrang.faUtilities.Moudle.FaCommand.Annotation.CmdNode;
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

    /**
     * 测试命令：HelloWorld
     * 输出问候信息到控制台
     * 
     * @param faCmdContext 命令上下文
     * @param name 参数名称
     */
    @CmdNode("fautilities.HelloWorld")
    public static void helloWorld(FaCmdContext faCmdContext,String name) {
        System.out.println("Hello World!");
        System.out.println(faCmdContext.sender().getName());
        System.out.println(name);
    }

    /**
     * 测试命令：HelloWorld2（带年龄参数）
     * 输出更详细的问候信息到控制台
     * 
     * @param faCmdContext 命令上下文
     * @param name 参数名称
     * @param age 参数年龄
     */
    @CmdNode("fautilities.HelloWorld2")
    public static void helloWorld(FaCmdContext faCmdContext,String name,int age) {
        System.out.println("Hello World!");
        System.out.println(faCmdContext.sender().getName());
        System.out.println(name);
        System.out.println(age);
    }


}
