package com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.basic;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaMessage.exception.FaDataParseException;
import com.wenkrang.faClip.module.FaRecipe.FaRecipe;
import com.wenkrang.faClip.module.FaRecipe.FaRecipeInstance;
import com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.FaReHandler;

public class ReTypeHandler implements FaReHandler {
    @Override
    public void handle(FaData faData, FaRecipe faRecipe, FaRecipeInstance faRecipeInstance) {
        if (faData.has("type")) {
            faRecipe.type = faData.getString("type");
        }else if (!faData.has("template")) {
            throw new FaDataParseException(faData, "type", "FaRecipe.Exception.FaReInterpreter.NotFound");
        }
    }
}
