package com.wenkrang.faClip.module.FaRecipe.event;

import com.wenkrang.faClip.module.FaRecipe.FaRecipeInstance;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CrafterCraftEvent;

public class ReCrafterCraftE implements Listener {
    private final FaRecipeInstance faRecipeInstance;

    public ReCrafterCraftE(FaRecipeInstance faRecipeInstance) {
        this.faRecipeInstance = faRecipeInstance;
    }


    @EventHandler
    public void onCrafterCraft(CrafterCraftEvent event) {
        ReCraftE.before(faRecipeInstance, event.getRecipe(), event);
    }
}
