package com.wenkrang.faClip.module.faResource;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 用于保存资源包的信息
 */
public class BukkitResource {
    public String name;
    public String url;
    public UUID id;
    public byte[] sha;

    public BukkitResource(String name, String url,String sha1) {
        this.name = name;
        this.url = url;
        sha = FaBukkitResourceManager.stringToHash(sha1);

        id = UUID.nameUUIDFromBytes(sha1.getBytes(StandardCharsets.UTF_8));
    }
}
