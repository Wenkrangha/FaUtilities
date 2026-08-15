package com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.typeHandler;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaMessage.exception.FaDataParseException;
import com.wenkrang.faClip.module.FaRecipe.FaRecipe;
import com.wenkrang.faClip.module.FaRecipe.FaRecipeInstance;
import com.wenkrang.faClip.module.FaRecipe.helper.RecipeHelper;
import com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.FaReHandler;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.List;

public class ShapeLessRecipeHandler implements FaReHandler {
    @Override
    public void handle(FaData faData, FaRecipe faRecipe, FaRecipeInstance faRecipeInstance) {
        String type = faRecipe.type;
        if (type != null) {
            if (type.equalsIgnoreCase("shapeless")) {
                if (!faData.has("define")) {
                    throw new FaDataParseException(faData, "define", "FaRecipe.Exception.FaReInterpreter.NotFound");
                }

                ItemStack result = RecipeHelper.getResult(faData, faRecipeInstance);

                // 初始化配方
                ShapelessRecipe recipe = new ShapelessRecipe(RecipeHelper.getNameSpacedKey(faRecipe), result);

                ConfigurationSection defineConfig = faData.getSection("define");
                if (defineConfig != null) {
                    for (String key : defineConfig.getKeys(false)) {
                        // 获取Choice
                        RecipeChoice choice = RecipeHelper.generateChoice(faRecipeInstance.getFaItemInstance(), key);
                        if (choice == null)
                            throw new FaDataParseException(faData, key, "FaRecipe.Exception.FaReInterpreter.InvalidDefine");
                        recipe.addIngredient(choice);
                    }
                }
            }
        }
    }
}
