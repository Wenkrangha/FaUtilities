package com.wenkrang.faClip.Helper;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class VersionHelper {
    // 使用正则表达式来判断字符串是否为数字
    public static boolean isNumeric(String str) {
        return str != null && str.matches("-?\\d+(\\.\\d+)?");
    }
    public static boolean isBelow(@NotNull String version) {
        if (!version.isEmpty()) {
            String[] split = version.split("\\.");

            String bukkit = Bukkit.getVersion();

            String[] currentVersion = bukkit.substring(bukkit.indexOf("("))
                            .replace("(", "")
                            .replace(")", "")
                            .replace(" ", "")
                            .replace("MC:", "")
                            .split("\\.");

            for (String s : split) {
                if (!isNumeric(s)) throw new RuntimeException("Cannot convert String to Integer: " + s);
            }
            for (String s : currentVersion) {
                if (!isNumeric(s)) throw new RuntimeException("Cannot convert String to Integer: " + s);
            }

            for (int i = 0;i < Math.min(split.length, currentVersion.length);i++) {
                if (Integer.parseInt(split[i]) > Integer.parseInt(currentVersion[i]))
                    return true;
                else if (Integer.parseInt(split[i]) < Integer.parseInt(currentVersion[i])) {
                    return false;
                }
            }
        }

        return false;
    }
}
