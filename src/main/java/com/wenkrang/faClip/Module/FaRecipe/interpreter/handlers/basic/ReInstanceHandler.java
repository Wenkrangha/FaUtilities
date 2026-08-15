package com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.basic;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaRecipe.FaRecipe;
import com.wenkrang.faClip.module.FaRecipe.FaRecipeInstance;
import com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.FaReHandler;

public class ReInstanceHandler implements FaReHandler {
    @Override
    public void handle(FaData faData, FaRecipe faRecipe, FaRecipeInstance faRecipeInstance) {
        faRecipe.faRecipeInstance = faRecipeInstance;
    }
}
