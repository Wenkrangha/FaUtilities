package com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.basic;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaMessage.exception.FaDataParseException;
import com.wenkrang.faClip.module.FaRecipe.FaRecipe;
import com.wenkrang.faClip.module.FaRecipe.FaRecipeInstance;
import com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.FaReHandler;

public class ReIDHandler implements FaReHandler {

    @Override
    public void handle(FaData faData, FaRecipe faRecipe, FaRecipeInstance faRecipeInstance) {
        if (faData.has("id")) {
            faRecipe.id = faData.getString("id");
        }else if (!faData.has("template")){
            throw new FaDataParseException(faData, "id", "FaRecipe.Exception.FaReInterpreter.NotFound");
        }
    }
}
