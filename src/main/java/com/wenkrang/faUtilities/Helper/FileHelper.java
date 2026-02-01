package com.wenkrang.faUtilities.Helper;

import java.io.File;
import java.io.IOException;

import static org.bukkit.Bukkit.getLogger;

public class FileHelper {
    public static void mkdirsWithLog(File target) throws IOException {
        if (!target.mkdirs()) {
            getLogger().warning("the temp directory can't be created");
        }
    }
}
