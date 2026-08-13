package com.wenkrang.faClip.module.FaResource.event;

import com.wenkrang.faClip.module.FaResource.BukkitResource;
import com.wenkrang.faClip.module.FaResource.FaBukkitResourceManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ResourceCommandPreprocessE implements Listener {
    private final FaBukkitResourceManager faBukkitResourceManager;

    public ResourceCommandPreprocessE(FaBukkitResourceManager faBukkitResourceManager) {
        this.faBukkitResourceManager = faBukkitResourceManager;
    }
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        String replace = event.getMessage().replace("/", "");
        String[] args = replace.split(" ");

        if (args.length != 2) return;

        if (args[0].equalsIgnoreCase("faresource")) {
            String name = args[1];

            BukkitResource resource = faBukkitResourceManager.getResource(name);

            if (resource != null) {
                faBukkitResourceManager.loadFor(resource, player, "");
            }

            event.setCancelled(true);
        }
    }
}
