package com.wenkrang.faClip.module.FaResource;

import com.wenkrang.faClip.module.FaMessage.exception.FaResourceException;
import com.wenkrang.faClip.module.FaResource.event.PlayerResourcePackStatusE;
import com.wenkrang.faClip.module.FaResource.event.ResourceCommandPreprocessE;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.UUID;

/**
 * 用于简化资源包加载的管理器
 */
public class FaBukkitResourceManager {
    // 注册的资源包列表
    private final ArrayList<BukkitResource> resourceList = new ArrayList<>();

    private final ResourceLoadRequestor resourceLoadRequestor;

    public void registerResource(BukkitResource resource) {
        resourceList.add(resource);
    }

    public void unregisterResource(BukkitResource resource) {
        resourceList.remove(resource);
    }

    public FaBukkitResourceManager(Plugin plugin) {
        this.resourceLoadRequestor = new ResourceLoadRequestor(plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerResourcePackStatusE(this), plugin);
        Bukkit.getPluginManager().registerEvents(new ResourceCommandPreprocessE(this), plugin);
    }

    public void askFor(BukkitResource bukkitResource, Player player, String des) {
        resourceLoadRequestor.requestLoad(player, bukkitResource,des);
    }

    public void loadFor(BukkitResource bukkitResource, Player player,String des) {
        player.addResourcePack(bukkitResource.id,
                bukkitResource.url,
                bukkitResource.sha,
                des,
                false);
    }

    /**
     * 根据名字获取资源
     * @param name 名字
     * @return 资源
     */
    public BukkitResource getResource(String name) {
        return resourceList.stream()
                .filter(i -> i.name.equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public BukkitResource getResource(UUID uuid) {
        return resourceList.stream()
                .filter(i -> i.id.equals(uuid))
                .findFirst()
                .orElse(null);
    }

    public ResourceLoadRequestor getResourceLoadRequestor() {
        return resourceLoadRequestor;
    }

    /**
     * 将十六进制字符串转换为字节数组（SHA1 哈希值）。
     *
     * @param hex 输入的十六进制字符串
     * @return 对应的字节数组
     * @throws FaResourceException 当输入字符串格式非法时抛出此异常
     */
    public static byte[] stringToHash(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            throw new FaResourceException("FaResource.Error.Hash.InvalidHexLength", hex);
        }
        byte[] data = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            int high = Character.digit(hex.charAt(i), 16);
            int low = Character.digit(hex.charAt(i + 1), 16);
            if (high == -1 || low == -1) {
                throw new FaResourceException("FaResource.Error.Hash.InvalidHexCharacter", hex);
            }
            data[i / 2] = (byte) ((high << 4) | low);
        }
        return data;
    }

}
