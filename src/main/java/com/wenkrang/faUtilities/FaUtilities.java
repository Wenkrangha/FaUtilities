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

    @CmdNode("test.test")
    public static void test(FaCmdContext faCmdContext) {
        System.out.println("Hello World!");
    }
    @CmdNode("test.test1")
    public static void test1(FaCmdContext faCmdContext) {
        System.out.println("Hello World!");
    }
    @CmdNode("test.test.test")
    public static void test2(FaCmdContext faCmdContext, String test) {
        System.out.println(test);
    }
    @CmdNode("test.test.test")
    public static void test3(FaCmdContext faCmdContext, String test, int a) {
        System.out.println(test + a);
    }
}
