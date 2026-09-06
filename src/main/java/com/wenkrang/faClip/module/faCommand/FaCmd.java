package com.wenkrang.faClip.module.faCommand;

import com.wenkrang.faClip.module.faCommand.interpreter.FaCmdInterpreter;
import com.wenkrang.faClip.module.faInterface.FaIntf;
import com.wenkrang.faClip.module.faMessage.exception.FaCmdException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * FaCmd 命令封装类
 * <p>通过 {@link Builder} 构建，构造完成后元数据不可变。
 * 内置权限决策行为（{@link #canExecute}、{@link #getRejectReason}）</p>
 */
public class FaCmd {

    // ==================== 不可变元数据（构造时确定） ====================

    /** 命令节点路径（如：plugin.command.subcommand） */
    private final String node;
    /** 命令名称（节点最后一段） */
    private final String name;
    /** 命令描述 */
    private final String description;
    /** 权限节点 */
    private final String permission;
    /** 是否需要 OP 权限 */
    private final boolean requireOP;
    /** 是否仅限玩家执行 */
    private final boolean forPlayer;
    /** 是否仅为帮助命令 */
    private final boolean onlyForHelp;
    /** 命令帮助信息 */
    private final String help;

    // ==================== 绑定引用（构造时确定） ====================

    /** 对应的接口 */
    private final FaIntf faIntf;
    /** 所属插件 */
    private final Plugin plugin;
    /** 所属命令实例 */
    private final FaCmdInstance faCmdInstance;
    /** 命令映射实例 */
    private final CommandMap commandMap;

    // ==================== 运行时字段（注册后赋值） ====================

    /** Bukkit 命令对象 */
    private Command command;
    /** 命令标签 */
    private String label;
    /** 激活的别名列表 */
    private List<String> activeAliases;

    // ==================== 构造（私有，通过 Builder） ====================

    private FaCmd(Builder builder) {
        this.node = builder.node;
        this.name = builder.name;
        this.description = builder.description;
        this.permission = builder.permission;
        this.requireOP = builder.requireOP;
        this.forPlayer = builder.forPlayer;
        this.onlyForHelp = builder.onlyForHelp;
        this.help = builder.help;
        this.faIntf = builder.faIntf;
        this.plugin = builder.plugin;
        this.faCmdInstance = builder.faCmdInstance;
        this.commandMap = builder.commandMap;
    }

    // ==================== 行为方法 ====================

    /**
     * 检查指定发送者是否有权限执行此命令
     *
     * @param sender 命令发送者
     * @return 如果可以执行返回 true
     */
    public boolean canExecute(@NotNull CommandSender sender) {
        return getRejectReason(sender) == null;
    }

    /**
     * 获取指定发送者被拒绝执行的原因
     *
     * @param sender 命令发送者
     * @return 拒绝原因的 i18n key，null 表示未被拒绝
     */
    public @Nullable String getRejectReason(@NotNull CommandSender sender) {
        if (requireOP && !sender.isOp()) return "FaCommand.Error.Interpreter.RequireOP";
        if (permission != null && !sender.hasPermission(permission)) return "FaCommand.Error.Interpreter.NoPermission";
        if (forPlayer && !(sender instanceof Player)) return "FaCommand.Error.Interpreter.OnlyForPlayer";
        return null;
    }

    /**
     * 获取命令的根节点（第一段）
     *
     * @return 根节点名称
     */
    public @Nullable String getRootNode() {
        return node != null ? node.split("\\.")[0] : null;
    }

    // ==================== Getter ====================

    public @NotNull String getNode() { return node; }

    public @Nullable String getName() { return name; }

    public @Nullable String getDescription() { return description; }

    public @Nullable String getPermission() { return permission; }

    public boolean isRequireOP() { return requireOP; }

    public boolean isForPlayer() { return forPlayer; }

    public boolean isOnlyForHelp() { return onlyForHelp; }

    public @Nullable String getHelp() { return help; }

    public @NotNull FaIntf getFaIntf() { return faIntf; }

    public Plugin getPlugin() { return plugin; }

    public FaCmdInstance getFaCmdInstance() { return faCmdInstance; }

    public CommandMap getCommandMap() { return commandMap; }

    public @Nullable Command getCommand() { return command; }

    public @Nullable String getLabel() { return label; }

    public @Nullable List<String> getActiveAliases() { return activeAliases; }

    // ==================== 运行时 Setter ====================

    public void setCommand(Command command) { this.command = command; }

    public void setLabel(String label) { this.label = label; }

    public void setActiveAliases(List<String> activeAliases) { this.activeAliases = activeAliases; }

    // ==================== Builder ====================

    /**
     * 创建一个 FaCmd Builder
     *
     * @param interpreter 命令解释器，用于获取 CommandMap 和 Plugin 等上下文
     * @return Builder 实例
     */
    public static @NotNull Builder builder(@NotNull FaCmdInterpreter interpreter) {
        return new Builder(interpreter);
    }

    /**
     * FaCmd 的构建器
     * <p>注解 Handler 通过此 Builder 设置命令的元数据属性，
     * 调用 {@link #build()} 后生成不可变的 FaCmd 实例。</p>
     */
    public static class Builder {
        private String node;
        private String name;
        private String description;
        private String permission;
        private boolean requireOP;
        private boolean forPlayer;
        private boolean onlyForHelp;
        private String help;
        private FaIntf faIntf;

        private final Plugin plugin;
        private final FaCmdInstance faCmdInstance;
        private final CommandMap commandMap;

        Builder(@NotNull FaCmdInterpreter interpreter) {
            this.faCmdInstance = interpreter.getFaCmdInstance();
            this.commandMap = faCmdInstance.getCommandManager().getCommandMap();
            this.plugin = faCmdInstance.getPlugin();
        }

        public @NotNull Builder node(@NotNull String node) {
            this.node = node;
            return this;
        }

        public @NotNull Builder name(@NotNull String name) {
            this.name = name;
            return this;
        }

        public @NotNull Builder description(@NotNull String description) {
            this.description = description;
            return this;
        }

        public @NotNull Builder permission(@NotNull String permission) {
            this.permission = permission;
            return this;
        }

        public @NotNull Builder requireOP() {
            this.requireOP = true;
            return this;
        }

        public @NotNull Builder forPlayer() {
            this.forPlayer = true;
            return this;
        }

        public @NotNull Builder onlyForHelp() {
            this.onlyForHelp = true;
            return this;
        }

        public @NotNull Builder help(@NotNull String help) {
            this.help = help;
            return this;
        }

        public @NotNull Builder faIntf(@NotNull FaIntf faIntf) {
            this.faIntf = faIntf;
            return this;
        }

        /**
         * 构建 FaCmd 实例
         *
         * @return 不可变的 FaCmd 实例
         * @throws NullPointerException 如果 node 未设置
         */
        public @NotNull FaCmd build() {
            if (node == null) throw new FaCmdException("FaCommand.Error.Interpreter.NodeNotInit");
            if (faIntf == null) throw new FaCmdException("FaCommand.Error.Interpreter.IntfNotFound");

            return new FaCmd(this);
        }

        // ==================== Builder 内部 Getter（供 Handler 查询当前状态） ====================

        public @Nullable String getNode() { return node; }

        public @Nullable Plugin getPlugin() { return plugin; }

        public @Nullable FaCmdInstance getFaCmdInstance() { return faCmdInstance; }

        public @NotNull CommandMap getCommandMap() { return commandMap; }
    }
}
