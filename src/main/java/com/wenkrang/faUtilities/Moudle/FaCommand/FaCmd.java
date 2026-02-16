package com.wenkrang.faUtilities.Moudle.FaCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaCmd {
    private String name;
    private String label;
    private List<String> activeAliases;
    private CommandMap commandMap;
    protected String description;
    private String permission;
    private FaCmdInterpreter interpreter;
    private String Node;
    private ArrayList<Method> methods;

    public FaCmd(FaCmdInterpreter faCmdInterpreter) {
        interpreter = faCmdInterpreter;
        commandMap = faCmdInterpreter.getFaCmdInstance().getCommandManager().getCommandMap();
        methods = new ArrayList<>();
    }

    public void removeMethod(Method method) {methods.remove(method);}

    public ArrayList<Method> getMethods() {
        return methods;
    }

    @Deprecated
    private void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
    }

    public void addMethod(Method method) {this.methods.add(method);}


    public String getNode() {
        return Node;
    }

    public void setNode(String node) {
        Node = node;
    }

    public FaCmdInterpreter getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(FaCmdInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    private Command command;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getActiveAliases() {
        return activeAliases;
    }

    public void setActiveAliases(List<String> activeAliases) {
        this.activeAliases = activeAliases;
    }

    public CommandMap getCommandMap() {
        return commandMap;
    }

    public void setCommandMap(CommandMap commandMap) {
        this.commandMap = commandMap;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }


}
