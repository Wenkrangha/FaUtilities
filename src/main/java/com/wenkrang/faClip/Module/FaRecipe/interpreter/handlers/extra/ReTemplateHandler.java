package com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.extra;

import com.wenkrang.faClip.module.FaData.FaData;
import com.wenkrang.faClip.module.FaRecipe.FaRecipe;
import com.wenkrang.faClip.module.FaRecipe.FaRecipeInstance;
import com.wenkrang.faClip.module.FaRecipe.interpreter.handlers.FaReHandler;

/**
 * 模板处理器：如果配置中指定了 template，则从模板文件中补全当前配方缺失的键
 */
public class ReTemplateHandler implements FaReHandler {
    @Override
    public void handle(FaData faData, FaRecipe faRecipe, FaRecipeInstance faRecipeInstance) {
        if (faData.has("template")) {
            String template = faData.getString("template");

            // 加载模板文件
            FaData templateData = FaData.getPluginResource(template, faRecipeInstance.getPlugin());

            faData.template(templateData);

            faRecipe.template = template;
        }
    }
}
