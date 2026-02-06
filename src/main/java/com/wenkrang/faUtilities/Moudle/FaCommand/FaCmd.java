package com.wenkrang.faUtilities.Moudle.FaCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FaCmd {
    private String name;
    private String label;
    private List<String> activeAliases;
    private CommandMap commandMap;
    protected String description;
    private String permission;
    private FaCmdInterpreter Interpreter;
    private String Node;
    private ArrayList<Method> methods;

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
    }


    public String getNode() {
        return Node;
    }

    public void setNode(String node) {
        Node = node;
    }

    public FaCmdInterpreter getInterpreter() {
        return Interpreter;
    }

    public void setInterpreter(FaCmdInterpreter interpreter) {
        this.Interpreter = interpreter;
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
