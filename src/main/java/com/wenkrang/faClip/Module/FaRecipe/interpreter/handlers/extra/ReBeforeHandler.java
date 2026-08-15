package com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.extra;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaRecipe.FaRecipe;
import com.wenkrang.faClip.module.FaRecipe.FaRecipeInstance;
import com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.FaReHandler;

public class ReBeforeHandler implements FaReHandler {
    @Override
    public void handle(FaData faData, FaRecipe faRecipe, FaRecipeInstance faRecipeInstance) {
        if (faData.has("before")) {
            faRecipe.events.put("before",faData.getString("before"));
        }
    }
}
