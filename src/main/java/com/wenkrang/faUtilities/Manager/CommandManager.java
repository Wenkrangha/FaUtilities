package com.wenkrang.faUtilities.Manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.List;

public class CommandManager {

    private CommandMap commandMap;

    public CommandManager() {
        try {
            // 获取 CommandMap
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        }catch (NoSuchFieldException | IllegalAccessException e) {

        }
    }
    public void registerAll(@NotNull String fallbackPrefix, @NotNull List<Command> commands) {
        commandMap.registerAll(fallbackPrefix, commands);
    }

    public boolean register(@NotNull String label, @NotNull String fallbackPrefix, @NotNull Command command) {
        return commandMap.register(label, fallbackPrefix, command);
    }

    public boolean register(@NotNull String fallbackPrefix, @NotNull Command command) {
        return commandMap.register(fallbackPrefix, command);
    }

    public boolean dispatch(@NotNull CommandSender sender, @NotNull String cmdLine) throws CommandException {
        return commandMap.dispatch(sender, cmdLine);
    }

    public void clearCommands() {
        commandMap.clearCommands();
    }

    @Nullable
    public Command getCommand(@NotNull String name) {
        return commandMap.getCommand(name);
    }

    @Nullable
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine) throws IllegalArgumentException {
        return commandMap.tabComplete(sender, cmdLine);
    }

    @Nullable
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine, @Nullable Location location) throws IllegalArgumentException {
        return commandMap.tabComplete(sender, cmdLine, location);
    }
}
