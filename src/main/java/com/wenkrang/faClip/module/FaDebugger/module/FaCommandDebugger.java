package com.wenkrang.faClip.module.FaDebugger.module;

import com.wenkrang.faClip.module.FaCommand.annotation.*;
import com.wenkrang.faClip.module.FaCommand.interpreter.FaCmdContext;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * FaCommand 模块调试命令集
 * 用于测试 FaCommand 框架的各项核心功能，包括注解、参数解析、权限控制、帮助生成等
 */
public class FaCommandDebugger {

    /**
     * 基础命令测试入口（仅用于帮助树展示）
     */
    @Cmd("facmdtest")
    @OnlyForHelp
    @Help("FaCommand 命令框架测试")
    public static void facmdtest() {
    }

    // ==================== 基础命令执行 ====================

    /**
     * 测试最基本的无参命令执行
     */
    @Cmd("facmdtest.basic.hello")
    @RequireOP
    @Debug
    @Help("基础无参命令测试")
    public static void basicHello() {
        System.out.println("[FaCommandDebugger] 基础命令执行成功: hello");
    }

    /**
     * 测试带 FaCmdContext 参数的命令，验证 sender 和 args 注入
     *
     * @param context 命令上下文，包含发送者和原始参数
     */
    @Cmd("facmdtest.basic.context")
    @RequireOP
    @Debug
    @Help("测试 FaCmdContext 上下文注入")
    public static void basicContext(FaCmdContext context) {
        System.out.println("[FaCommandDebugger] sender: " + context.sender().getName());
        System.out.println("[FaCommandDebugger] args length: " + context.args().length);
        for (int i = 0; i < context.args().length; i++) {
            System.out.println("[FaCommandDebugger]   arg[" + i + "]: " + context.args()[i]);
        }
    }

    // ==================== 参数类型解析 ====================

    /**
     * 测试 String 类型参数自动解析
     *
     * @param message 要输出的字符串消息
     */
    @Cmd("facmdtest.param.string")
    @RequireOP
    @Debug
    @Help("测试 String 参数解析")
    public static void paramString(@ParamDes("消息内容") String message) {
        System.out.println("[FaCommandDebugger] String 参数: " + message);
    }

    /**
     * 测试 int 类型参数自动解析
     *
     * @param number 要输出的整数
     */
    @Cmd("facmdtest.param.int")
    @RequireOP
    @Debug
    @Help("测试 int 参数解析")
    public static void paramInt(@ParamDes("整数") int number) {
        System.out.println("[FaCommandDebugger] int 参数: " + number);
    }

    /**
     * 测试 double 类型参数自动解析
     *
     * @param value 要输出的小数
     */
    @Cmd("facmdtest.param.double")
    @RequireOP
    @Debug
    @Help("测试 double 参数解析")
    public static void paramDouble(@ParamDes("小数") double value) {
        System.out.println("[FaCommandDebugger] double 参数: " + value);
    }

    /**
     * 测试 boolean 类型参数自动解析
     *
     * @param flag 布尔值
     */
    @Cmd("facmdtest.param.boolean")
    @RequireOP
    @Debug
    @Help("测试 boolean 参数解析")
    public static void paramBoolean(@ParamDes("true/false") boolean flag) {
        System.out.println("[FaCommandDebugger] boolean 参数: " + flag);
    }

    /**
     * 测试多个不同类型参数混合解析
     *
     * @param name  名称字符串
     * @param count 数量
     * @param rate  比率
     */
    @Cmd("facmdtest.param.multi")
    @RequireOP
    @Debug
    @Help("测试多参数混合解析")
    public static void paramMulti(
            @ParamDes("名称") String name,
            @ParamDes("数量") int count,
            @ParamDes("比率") double rate
    ) {
        System.out.println("[FaCommandDebugger] 多参数: name=" + name + ", count=" + count + ", rate=" + rate);
    }

    /**
     * 测试 FaCmdContext 与普通参数混合注入
     *
     * @param context 命令上下文
     * @param message 消息内容
     * @param repeat  重复次数
     */
    @Cmd("facmdtest.param.mixed")
    @RequireOP
    @Debug
    @Help("测试 Context + 参数混合注入")
    public static void paramMixed(
            FaCmdContext context,
            @ParamDes("消息") String message,
            @ParamDes("重复次数") int repeat
    ) {
        System.out.println("[FaCommandDebugger] sender=" + context.sender().getName());
        for (int i = 0; i < repeat; i++) {
            System.out.println("[FaCommandDebugger] [" + (i + 1) + "] " + message);
        }
    }

    // ==================== Bukkit 类型参数解析 ====================

    /**
     * 测试 Player 类型参数自动解析（通过在线玩家名匹配）
     *
     * @param target 目标玩家
     */
    @Cmd("facmdtest.bukkit.player")
    @RequireOP
    @Debug
    @Help("测试 Player 参数解析")
    public static void bukkitPlayer(@ParamDes("玩家名") Player target) {
        System.out.println("[FaCommandDebugger] Player: " + target.getName()
                + ", UUID: " + target.getUniqueId());
    }

    /**
     * 测试 GameMode 枚举类型参数解析
     *
     * @param context  命令上下文
     * @param gameMode 游戏模式
     */
    @Cmd("facmdtest.bukkit.gamemode")
    @RequireOP
    @Debug
    @ForPlayer
    @Help("测试 GameMode 参数解析并切换模式")
    public static void bukkitGameMode(FaCmdContext context, @ParamDes("模式") GameMode gameMode) {
        Player player = (Player) context.sender();
        player.setGameMode(gameMode);
        System.out.println("[FaCommandDebugger] 已将 " + player.getName() + " 切换为 " + gameMode);
    }

    /**
     * 测试 Material 枚举类型参数解析
     *
     * @param material 物品材质
     */
    @Cmd("facmdtest.bukkit.material")
    @RequireOP
    @Debug
    @Help("测试 Material 参数解析")
    public static void bukkitMaterial(@ParamDes("材质名") Material material) {
        System.out.println("[FaCommandDebugger] Material: " + material.name()
                + ", isBlock=" + material.isBlock()
                + ", isItem=" + material.isItem());
    }

    // ==================== 权限与访问控制 ====================

    /**
     * 测试 @CmdPermission 权限节点拦截
     */
    @Cmd("facmdtest.perm.permission")
    @CmdPermission("faclip.debug.test")
    @Debug
    @Help("测试 @CmdPermission 权限检查")
    public static void permPermission() {
        System.out.println("[FaCommandDebugger] 权限检查通过，命令已执行");
    }

    /**
     * 测试 @RequireOP 拦截（非 OP 不可执行）
     */
    @Cmd("facmdtest.perm.op")
    @RequireOP
    @Debug
    @Help("测试 @RequireOP 权限检查")
    public static void permOp() {
        System.out.println("[FaCommandDebugger] OP 检查通过，命令已执行");
    }

    /**
     * 测试 @ForPlayer 拦截（仅玩家可执行，控制台不可执行）
     */
    @Cmd("facmdtest.perm.playeronly")
    @RequireOP
    @Debug
    @ForPlayer
    @Help("测试 @ForPlayer 仅玩家可执行")
    public static void permPlayerOnly(FaCmdContext context) {
        Player player = (Player) context.sender();
        System.out.println("[FaCommandDebugger] 仅玩家命令，执行者: " + player.getName());
    }

    /**
     * 测试无任何权限限制的命令（所有人都可执行）
     */
    @Cmd("facmdtest.perm.open")
    @Debug
    @Help("无权限限制命令")
    public static void permOpen() {
        System.out.println("[FaCommandDebugger] 无权限限制命令已执行");
    }

    // ==================== 帮助系统 ====================

    /**
     * 测试 @OnlyForHelp 节点展示帮助树
     * 执行此命令时应输出子命令列表而非执行逻辑
     */
    @Cmd("facmdtest.help")
    @OnlyForHelp
    @Debug
    @Help("帮助系统测试（展示子命令列表）")
    public static void helpRoot() {
    }

    /**
     * 帮助子命令 A
     */
    @Cmd("facmdtest.help.subA")
    @Debug
    @Help("帮助子命令 A")
    public static void helpSubA() {
        System.out.println("[FaCommandDebugger] 子命令 A 已执行");
    }

    /**
     * 帮助子命令 B（带参数描述）
     *
     * @param target 目标参数
     * @param count  数量参数
     */
    @Cmd("facmdtest.help.subB")
    @Debug
    @Help("帮助子命令 B（带参数描述）")
    public static void helpSubB(
            @ParamDes("目标") String target,
            @ParamDes("数量") int count
    ) {
        System.out.println("[FaCommandDebugger] 子命令 B: target=" + target + ", count=" + count);
    }

    /**
     * 帮助子命令 C（嵌套的 OnlyForHelp 节点）
     */
    @Cmd("facmdtest.help.nested")
    @OnlyForHelp
    @Debug
    @Help("嵌套帮助节点")
    public static void helpNested() {
    }

    /**
     * 嵌套帮助节点下的子命令
     */
    @Cmd("facmdtest.help.nested.deep")
    @Debug
    @Help("深层嵌套子命令")
    public static void helpNestedDeep() {
        System.out.println("[FaCommandDebugger] 深层嵌套子命令已执行");
    }

    // ==================== 参数描述注解 ====================

    /**
     * 测试 @ParamArrayDes 多候选值描述（Tab 补全时应展示候选列表）
     *
     * @param action 要执行的动作
     */
    @Cmd("facmdtest.des.array")
    @RequireOP
    @Debug
    @Help("测试 @ParamArrayDes 候选值描述")
    public static void desArray(@ParamArrayDes({"start", "stop", "restart", "status"}) String action) {
        System.out.println("[FaCommandDebugger] 执行动作: " + action);
    }

    // ==================== 综合场景 ====================

    /**
     * 综合测试：玩家专属 + 权限检查 + 多参数 + Context 混合
     *
     * @param context  命令上下文
     * @param target   目标玩家名
     * @param material 物品材质
     * @param amount   数量
     */
    @Cmd("facmdtest.complex.give")
    @RequireOP
    @Debug
    @ForPlayer
    @Help("综合测试：模拟给予物品命令")
    public static void complexGive(
            FaCmdContext context,
            @ParamDes("目标玩家") String target,
            @ParamDes("物品") Material material,
            @ParamDes("数量") int amount
    ) {
        Player sender = (Player) context.sender();
        System.out.println("[FaCommandDebugger] 综合测试:");
        System.out.println("[FaCommandDebugger]   发送者: " + sender.getName());
        System.out.println("[FaCommandDebugger]   目标: " + target);
        System.out.println("[FaCommandDebugger]   物品: " + material.name());
        System.out.println("[FaCommandDebugger]   数量: " + amount);
    }

    /**
     * 综合测试：返回 boolean 结果验证
     *
     * @param context 命令上下文
     * @return 始终返回 true
     */
    @Cmd("facmdtest.complex.return")
    @RequireOP
    @Debug
    @Help("测试命令方法返回 boolean 值")
    public static boolean complexReturn(FaCmdContext context) {
        System.out.println("[FaCommandDebugger] 命令返回 true");
        return true;
    }
}
