package com.wenkrang.faClip.manager;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.CraftingRecipe;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class RecipeManager {
    private final Plugin plugin;

    private final Map<String, Recipe> recipes = new HashMap<>();
    private final Map<String, NamespacedKey> namespacedKeys = new HashMap<>();

    public RecipeManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * 刷新配方书
     */
    public void refresh() {
        Bukkit.getOnlinePlayers().forEach(i -> i.discoverRecipes(namespacedKeys.values()));
    }

    public void register(CraftingRecipe recipe) {
        plugin.getServer().addRecipe(recipe);

        recipes.put(recipe.getKey().getKey(), recipe);
        namespacedKeys.put(recipe.getKey().getKey(), recipe.getKey());
    }

    public void register(CookingRecipe<?> recipe) {
        plugin.getServer().addRecipe(recipe);

        recipes.put(recipe.getKey().getKey(), recipe);
        namespacedKeys.put(recipe.getKey().getKey(), recipe.getKey());
    }

    public void register(Recipe recipe,NamespacedKey namespacedKey) {
        plugin.getServer().addRecipe(recipe);

        recipes.put(namespacedKey.getKey(), recipe);
        namespacedKeys.put(namespacedKey.getKey(), namespacedKey);
    }

    public Recipe getRecipe(String key) {
        return recipes.get(key);
    }

    public void unregister(String key) {
        plugin.getServer().removeRecipe(namespacedKeys.get(key));
        recipes.remove(key);
    }

    public void unregisterAll() {
        for (NamespacedKey namespacedKey : namespacedKeys.values()) {
            plugin.getServer().removeRecipe(namespacedKey);
        }
        recipes.clear();
        namespacedKeys.clear();
    }

    public Map<String, Recipe> getRecipes() {
        return recipes;
    }

    public Map<String, NamespacedKey> getNamespacedKeys() {
        return namespacedKeys;
    }
}
