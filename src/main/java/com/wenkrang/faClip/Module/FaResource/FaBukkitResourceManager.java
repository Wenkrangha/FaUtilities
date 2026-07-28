package com.wenkrang.faClip.Module.FaResource;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 用于简化资源包加载的管理器
 */
public class FaBukkitResourceManager {
    // 注册的资源包列表
    private final ArrayList<BukkitResource> resourceList = new ArrayList<>();

    public void registerResource(BukkitResource resource) {
        resourceList.add(resource);
    }

    public void unregisterResource(BukkitResource resource) {
        resourceList.remove(resource);
    }

    public FaBukkitResourceManager(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(new PlayerResourcePackStatusE(), plugin);
    }



    /**
     * 用于保存资源包的信息
     */
    public static class BukkitResource {
        public String name;
        public UUID id;
        public URL url;

        public BukkitResource(String name, UUID id, URL url) {
            this.name = name;
            this.id = id;
            this.url = url;
        }
    }
}
