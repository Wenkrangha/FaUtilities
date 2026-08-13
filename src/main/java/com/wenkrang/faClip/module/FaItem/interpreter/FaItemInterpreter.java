package com.wenkrang.faClip.module.FaItem.interpreter;

import com.wenkrang.faClip.module.FaItem.FaItem;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.FaItemHandler;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.basic.IdHandler;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.basic.LoreHandler;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.basic.NameHandler;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.basic.TypeHandler;
import com.wenkrang.faClip.module.FaItem.interpreter.handler.extra.*;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class FaItemInterpreter {
    private final ArrayList<FaItemHandler> handlers = new ArrayList<>();

    public Plugin plugin;

    public FaItemInterpreter(Plugin p) {
        handlers.add(new TypeHandler());
        handlers.add(new NameHandler());
        handlers.add(new IdHandler());
        handlers.add(new LoreHandler());
        handlers.add(new TagHandler());
        handlers.add(new PlayerItemClickEvent());
        handlers.add(new PlayerInvClickEvent());
        handlers.add(new RefHandler());
        handlers.add(new BackRefHandler());
        handlers.add(new GroupHandler());
        handlers.add(new IsolateHandler());

        plugin = p;
    }

    public FaItem interpreter(YamlConfiguration yamlConfiguration) {
        FaItem faItem = new FaItem(plugin, Material.AIR);

        for (FaItemHandler faItemHandler : handlers) {
            faItemHandler.handle(faItem, yamlConfiguration, this);
        }

        return faItem;
    }
}
