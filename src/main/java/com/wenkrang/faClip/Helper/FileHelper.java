package com.wenkrang.faClip.Helper;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import static com.wenkrang.faClip.Module.FaMessage.Fm.warning;

/**
 * 文件操作帮助类
 * 提供文件创建、目录创建等工具方法
 */
public class FileHelper {
    /**
     * 创建目录, 如果目录已存在则不创建
     *
     * @param target 目标目录
     */
    public static void mkdirsWithLog(@NotNull File target) {
        if (!target.mkdirs()) {
            warning("the temp directory can't be created");
        }
    }
}
