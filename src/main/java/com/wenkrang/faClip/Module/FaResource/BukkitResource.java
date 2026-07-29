package com.wenkrang.faClip.Module.FaResource;

import java.util.UUID;

/**
 * 用于保存资源包的信息
 */
public class BukkitResource {
    public String name;
    public String url;
    public UUID id = UUID.randomUUID();
    public byte[] sha;

    public BukkitResource(String name, String url,String sha1) {
        this.name = name;
        this.url = url;
        sha = FaBukkitResourceManager.stringToHash(sha1);
    }
}
