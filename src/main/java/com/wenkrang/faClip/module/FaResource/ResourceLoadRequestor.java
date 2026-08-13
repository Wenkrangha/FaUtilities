package com.wenkrang.faClip.module.FaResource;

import com.wenkrang.faClip.helper.VersionHelper;
import com.wenkrang.faClip.module.FaMessage.Helper.Scc;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

public class ResourceLoadRequestor {
    private final Plugin plugin;

    public ResourceLoadRequestor(Plugin plugin) {
        this.plugin = plugin;
    }

    public void requestLoad(Player player, BukkitResource bukkitResource, String des) {
        ItemStack gui = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) gui.getItemMeta();

        if (meta != null) {
            ComponentBuilder builder = new ComponentBuilder()
                    .append(Scc.BOLD + "   【资源包安装请求】" + Scc.RESET)
                    .append("\n").append("\n")
                    .append("插件" + Scc.BOLD + plugin.getName() + Scc.RESET)
                    .append("希望您安装材质包，您可以点击下方选项进行选择。")
                    .append("\n").append("\n")
                    .append(Scc.GREY + des)
                    .append("\n").append("\n");

            if (!VersionHelper.isBelow("1.20.4")) {
                builder.append("       【自动安装】" + Scc.RESET)
                        .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/faresource " + bukkitResource.name));
            }

            builder.append("\n")
                    .append("      【浏览器下载】" + Scc.RESET)
                    .event(new ClickEvent(ClickEvent.Action.OPEN_URL, bukkitResource.url));

            BaseComponent[] page = builder.create();

            meta.spigot().addPage(page);

            gui.setItemMeta(meta);
            player.openBook(gui);
        }
    }

    public static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        int padding = (width - text.length()) / 2;
        String format = "%" + (padding + text.length()) + "s";
        return String.format(format, text);
    }
}
