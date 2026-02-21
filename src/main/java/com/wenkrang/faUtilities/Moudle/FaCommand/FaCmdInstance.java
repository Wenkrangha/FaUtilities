package com.wenkrang.faUtilities.Moudle.FaCommand;

import com.wenkrang.faUtilities.Helper.ClassHelper;
import com.wenkrang.faUtilities.Manager.CommandManager;
import com.wenkrang.faUtilities.Moudle.FaCommand.AnnotationHandler.CmdNodeHandler;
import com.wenkrang.faUtilities.Moudle.FaCommand.FaCmdInterpreter.FaCmdInterpreter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class FaCmdInstance {
    private final CommandManager commandManager;
    private final Plugin plugin;
    private ArrayList<String> nodes = new ArrayList<>();
    private final ArrayList<FaCmd> faCmds = new ArrayList<>();
    private FaCmdInterpreter faCmdInterpreter;

    public ArrayList<String> getNodes() {
        return nodes;
    }

    private void setNodes(ArrayList<String> nodes) {
        this.nodes = nodes;
    }

    private void removeNode(String node) {
        if (faCmds.stream().filter(i -> i.getNode().equals(node)).count() == 1) nodes.remove(node);
    }

    private void addNode(String node) {
        if (!nodes.contains(node)) nodes.add(node);
    }

    /**
     * 获取指定节点的FaCmd实例
     * @param node 节点
     * @return FaCmd实例
     */
    public FaCmd getFaCmd(String node) {
        return faCmds.stream().filter(i -> i.getNode().equals(node)).findAny().orElse(null);
    }

    public ArrayList<FaCmd> getFaCmds() {
        return faCmds;
    }

    public void addFaCmd(FaCmd faCmd) {
        faCmds.add(faCmd);
        addNode(faCmd.getNode());
    }

    public void removeFaCmd(FaCmd faCmd) {
        faCmds.remove(faCmd);
        removeNode(faCmd.getNode());
    }



    private FaCmdInstance(Plugin plugin) {
        commandManager = new CommandManager();
        this.plugin = plugin;
    }

    public static FaCmdInstance create(Plugin plugin) {
        FaCmdInstance faCmdInstance = new FaCmdInstance(plugin);
        faCmdInstance.faCmdInterpreter = new FaCmdInterpreter(faCmdInstance, plugin);

        faCmdInstance.faCmdInterpreter.addAnnotationHandlers(new CmdNodeHandler());

        return faCmdInstance;
    }

    public void close() {
        for (FaCmd faCmd : faCmds) {
            if (faCmd.getCommand().isRegistered()) faCmd.getCommand().unregister(commandManager.getCommandMap());
        }
    }

    public void enableFor(Class<?>[] commandClasses) {
        for (Class<?> commandClass : commandClasses) {
            final Method[] methods = commandClass.getMethods();
            for (Method method : methods) {
                Bukkit.getScheduler().runTask(plugin, () -> {faCmdInterpreter.initialize(method);});
            }
        }
        // 刷新指令
        Bukkit.getOnlinePlayers().forEach(Player::updateCommands);
    }

    public void enableForAll(Plugin plugin) {
        enableFor(ClassHelper.getClasses(plugin.getClass()).toArray(Class[]::new));
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public Plugin getPlugin() {
        return plugin;
    }
}
