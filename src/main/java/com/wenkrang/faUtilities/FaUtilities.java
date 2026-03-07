package com.wenkrang.faUtilities;

import com.wenkrang.faUtilities.Moudle.FaCommand.Annotation.CmdNode;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInstance;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInterpreter.FaCmdContext;
import org.bukkit.plugin.java.JavaPlugin;


public final class FaUtilities extends JavaPlugin {

    FaCmdInstance faCmdInstance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        faCmdInstance = FaCmdInstance.create(this);
        faCmdInstance.enableForAll(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        faCmdInstance.close();
    }

    @CmdNode("fautilities.HelloWorld")
    public static void helloWorld(FaCmdContext faCmdContext,String name) {
        System.out.println("Hello World!");
        System.out.println(faCmdContext.sender().getName());
        System.out.println(name);
    }

    @CmdNode("fautilities.HelloWorld2")
    public static void helloWorld(FaCmdContext faCmdContext,String name,int age) {
        System.out.println("Hello World!");
        System.out.println(faCmdContext.sender().getName());
        System.out.println(name);
        System.out.println(age);
    }


}
