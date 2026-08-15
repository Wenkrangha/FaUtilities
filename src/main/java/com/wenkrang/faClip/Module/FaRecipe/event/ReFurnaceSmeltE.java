package com.wenkrang.faClip.module.FaRecipe.event;

import com.wenkrang.faClip.module.FaRecipe.FaRecipeInstance;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;

public class ReFurnaceSmeltE implements Listener {
    private final FaRecipeInstance faRecipeInstance;

    public ReFurnaceSmeltE(FaRecipeInstance faRecipeInstance) {
        this.faRecipeInstance = faRecipeInstance;
    }

    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent event) {
    }
}
