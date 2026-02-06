package com.wenkrang.faUtilities.Helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class i18nHelper {
    //设置语言
    private final Locale locale;

    private final ResourceBundle resourceBundle;

    public i18nHelper() {
        locale = Locale.getDefault();
        resourceBundle = ResourceBundle.getBundle("language", locale, this.getClass().getClassLoader());
    }

    public i18nHelper(Locale locale, ResourceBundle resourceBundle) {
        this.locale = locale;
        this.resourceBundle = resourceBundle;
    }

    //获取i18n文本
    public String pt(String Message) {
        return resourceBundle.getString(Message);
    }

    //获取i18n文本
    public static String t(String Message) {
        return new i18nHelper().pt(Message);
    }
}
