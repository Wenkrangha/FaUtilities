package com.wenkrang.faClip.module.faMessage;

import com.wenkrang.faClip.module.faMessage.helper.I18nHelper;
import com.wenkrang.faClip.module.faMessage.helper.Scc;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

public class Fm {
    /**
     * 消息输出时的前缀标识
     */
    public static String prefix = "[FaClip]";

    /**
     * 报错时是否输出完整堆栈（由 FaClip 主类根据 debug 配置同步）
     */
    public static boolean reportStackTrace = false;

    /**
     * 获取消息前缀
     *
     * @return 消息前缀字符串
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * 向控制台发送普通日志消息
     *
     * @param msg 要输出的消息内容
     */
    public static void log(String msg) {
        Bukkit.getConsoleSender().sendMessage(prefix + " " + msg);
    }

    /**
     * 向指定玩家发送普通日志消息
     *
     * @param player 接收消息的玩家对象
     * @param msg 要输出的消息内容
     */
    public static void log(Player player, String msg) {
        player.sendMessage(prefix + " " + msg);
    }

    /**
     * 向命令发送者发送普通日志消息（自动判断是玩家还是控制台）
     *
     * @param sender 接收消息的命令发送者
     * @param msg 要输出的消息内容
     */
    public static void log(CommandSender sender, String msg) {
        sender.sendMessage(prefix + " " + msg);
    }

    /**
     * 向控制台发送信息状态消息（带蓝色[*]标记）
     *
     * @param msg 要输出的信息内容
     */
    public static void info(String msg) {
        Bukkit.getConsoleSender().sendMessage(Scc.BLUE + "[*] " + Scc.RESET + msg);
    }

    /**
     * 向指定玩家发送信息状态消息（带蓝色[*]标记）
     *
     * @param player 接收消息的玩家对象
     * @param msg 要输出的信息内容
     */
    public static void info(Player player, String msg) {
        player.sendMessage(Scc.BLUE + "[*] " + Scc.RESET + msg);
    }

    /**
     * 向命令发送者发送信息状态消息（带蓝色[*]标记）
     *
     * @param sender 接收消息的命令发送者
     * @param msg 要输出的信息内容
     */
    public static void info(CommandSender sender, String msg) {
        sender.sendMessage(Scc.BLUE + "[*] " + Scc.RESET + msg);
    }

    /**
     * 向控制台发送错误状态消息（带红色[-]标记）
     *
     * @param msg 要输出的错误内容
     */
    public static void error(String msg) {
        Bukkit.getConsoleSender().sendMessage(Scc.RED + "[-] " + Scc.RESET + msg);
    }

    /**
     * 向指定玩家发送错误状态消息（带红色[-]标记）
     *
     * @param player 接收消息的玩家对象
     * @param msg 要输出的错误内容
     */
    public static void error(Player player, String msg) {
        player.sendMessage(Scc.RED + "[-] " + Scc.RESET + msg);
    }

    /**
     * 向命令发送者发送错误状态消息（带红色[-]标记）
     *
     * @param sender 接收消息的命令发送者
     * @param msg 要输出的错误内容
     */
    public static void error(CommandSender sender, String msg) {
        sender.sendMessage(Scc.RED + "[-] " + Scc.RESET + msg);
    }

    /**
     * 向控制台发送警告状态消息（黄色[!]标记）
     *
     * @param msg 要输出的警告内容
     */
    public static void warning(String msg) {
        Bukkit.getConsoleSender().sendMessage(Scc.YELLOW + "[!] " + Scc.RESET + msg);
    }

    /**
     * 向指定玩家发送警告状态消息（带黄色[!]标记）
     *
     * @param player 接收消息的玩家对象
     * @param msg 要输出的警告内容
     */
    public static void warning(Player player, String msg) {
        player.sendMessage(Scc.YELLOW + "[!] " + Scc.RESET + msg);
    }

    /**
     * 向命令发送者发送警告状态消息（带黄色[!]标记）
     *
     * @param sender 接收消息的命令发送者
     * @param msg 要输出的警告内容
     */
    public static void warning(CommandSender sender, String msg) {
        sender.sendMessage(Scc.YELLOW + "[!] " + Scc.RESET + msg);
    }

    /**
     * 向控制台发送调试状态消息（带灰色[/]标记）
     *
     * @param msg 要输出的调试内容
     */
    public static void debug(String msg) {
        Bukkit.getConsoleSender().sendMessage(Scc.GREY + "[/] " + Scc.RESET + msg);
    }

    /**
     * 向指定玩家发送调试状态消息（带灰色[/]标记）
     *
     * @param player 接收消息的玩家对象
     * @param msg 要输出的调试内容
     */
    public static void debug(Player player, String msg) {
        player.sendMessage(Scc.GREY + "[/] " + Scc.RESET + msg);
    }

    /**
     * 向命令发送者发送调试状态消息（带灰色[/]标记）
     *
     * @param sender 接收消息的命令发送者
     * @param msg 要输出的调试内容
     */
    public static void debug(CommandSender sender, String msg) {
        sender.sendMessage(Scc.GREY + "[/] " + Scc.RESET + msg);
    }

    /**
     * 向控制台发送详细格式化的多行消息
     * 消息包含标题、副标题、主体内容和次要内容，使用分隔线框起来
     *
     * @param title 主标题（加粗显示）
     * @param subtitle 副标题（灰色显示）
     * @param body 主体内容
     * @param subBody 次要内容
     */
    public static void detail(String title, String subtitle, String body, String subBody) {
        Bukkit.getConsoleSender().sendMessage("-----------------------------------------------------------------\n\n"
                + Scc.BOLD + title + Scc.RESET + "\n" + Scc.GREY + subtitle + Scc.RESET + "\n"
        + body + "\n" + subBody + "\n\n" + "-----------------------------------------------------------------");
    }

    /**
     * 向玩家发送详细格式化的多行消息
     * 消息包含标题、副标题、主体内容和次要内容，使用分隔线框起来
     *
     * @param title 主标题（加粗显示）
     * @param subtitle 副标题（灰色显示）
     * @param body 主体内容
     * @param subBody 次要内容
     */
    public static void detail(Player player, String title, String subtitle, String body, String subBody) {
        player.sendMessage("-----------------------------------------------------------------\n\n"
                + Scc.BOLD + title + Scc.RESET + "\n" + Scc.GREY + subtitle + Scc.RESET + "\n"
                + body + "\n" + subBody + "\n\n" + "-----------------------------------------------------------------");
    }

    public static Set<CommandSender> getAllSenders() {
        Set<CommandSender> senders = new HashSet<>();
        senders.add(Bukkit.getConsoleSender());
        senders.addAll(Bukkit.getOnlinePlayers());

        return senders;
    }

    // ==================== 统一报错上报 ====================

    /**
     * 向控制台上报错误（i18n 键 + 格式化参数）
     * <p>适用于可预期的业务错误，如配置缺失、注册失败等</p>
     *
     * @param key i18n 资源键
     * @param args 格式化参数
     */
    public static void reportError(String key, Object... args) {
        error(I18nHelper.ft(key, args));
    }

    /**
     * 向控制台上报警告（i18n 键 + 格式化参数）
     * <p>适用于不影响流程但需要关注的问题，如跳过一个非法条目</p>
     *
     * @param key i18n 资源键
     * @param args 格式化参数
     */
    public static void reportWarning(String key, Object... args) {
        warning(I18nHelper.ft(key, args));
    }

    /**
     * 向控制台上报异常
     * <p>统一替代裸 e.printStackTrace()：始终输出异常消息，
     * 仅在 {@link #reportStackTrace} 开启时输出完整堆栈</p>
     *
     * @param e 要上报的异常
     */
    public static void reportError(Throwable e) {
        error(e.toString());

        if (reportStackTrace) {
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            Bukkit.getConsoleSender().sendMessage(Scc.RED + writer + Scc.RESET);
        }
    }
}
