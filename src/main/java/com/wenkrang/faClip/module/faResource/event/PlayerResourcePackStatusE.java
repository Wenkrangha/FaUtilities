package com.wenkrang.faClip.module.faResource.event;

import com.wenkrang.faClip.module.faMessage.helper.Scc;
import com.wenkrang.faClip.module.faResource.FaBukkitResourceManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import static org.bukkit.event.player.PlayerResourcePackStatusEvent.Status.*;

public class PlayerResourcePackStatusE implements Listener {
    private final FaBukkitResourceManager faBukkitResourceManager;

    public PlayerResourcePackStatusE(FaBukkitResourceManager faBukkitResourceManager) {
        this.faBukkitResourceManager = faBukkitResourceManager;
    }

    @EventHandler
    public void onPlayerResourcePackStatus(PlayerResourcePackStatusEvent event) {
        if (faBukkitResourceManager.getResource(event.getID()) != null) {
            PlayerResourcePackStatusEvent.Status status = event.getStatus();
            if (status == DECLINED ||
            status == DISCARDED ||
            status == FAILED_DOWNLOAD ||
            status == FAILED_RELOAD) {
                Player player = event.getPlayer();
                // 发送消息
                player.spigot().sendMessage(new ComponentBuilder()
                        .append(Scc.BLUE + Scc.BOLD + "[*]")
                        .append(" 资源包安装失败，您可以点击右侧按钮手动安装")
                        .append(Scc.GREY + "【浏览器下载】")
                        .event(new ClickEvent(ClickEvent.Action.OPEN_URL
                                , faBukkitResourceManager.getResource(event.getID()).url))
                        .create()
                );
            }
        }
    }
}
