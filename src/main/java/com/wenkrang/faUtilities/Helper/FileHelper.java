package com.wenkrang.faUtilities.Helper;

import java.io.File;

import static org.bukkit.Bukkit.getLogger;

public class FileHelper {
    /**
     * 创建目录, 如果目录已存在则不创建
     *
     * @param target 目标目录
     */
    public static void mkdirsWithLog(File target) {
        if (!target.mkdirs()) {
            getLogger().warning("the temp directory can't be created");
        }
    }
}
