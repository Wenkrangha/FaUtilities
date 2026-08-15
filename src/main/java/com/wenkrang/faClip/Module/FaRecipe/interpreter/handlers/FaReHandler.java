package com.wenkrang.faClip.module.FaRecipe.interpreter.handlers;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaRecipe.FaRecipe;
import com.wenkrang.faClip.module.FaRecipe.FaRecipeInstance;

public interface FaReHandler {
    void handle(FaData faData, FaRecipe faRecipe, FaRecipeInstance faRecipeInstance);
}
