package com.wenkrang.faClip.Module.FaInterface.AnnotationHandler;

import com.wenkrang.faClip.Module.FaCommand.Helper.NodeHelper;
import com.wenkrang.faClip.Module.FaInterface.Annotaion.Intf;
import com.wenkrang.faClip.Module.FaInterface.FaIntf;
import com.wenkrang.faClip.Module.FaMessage.Fm;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static com.wenkrang.faClip.Module.FaMessage.Helper.i18nHelper.ft;
import static com.wenkrang.faClip.Module.FaMessage.Helper.i18nHelper.t;

public class intfHandler implements SimpleAnnotationHandler {
    /**
     * 检查接口注册是否合规
     * @param method
     * @param node
     * @return
     */
    public static boolean check(Method method,String node) {
        if (!Modifier.isStatic(method.getModifiers()))
            throw new RuntimeException(ft("FaInterface.Error.Interpreter.NotStatic", method.getName()));
        if (!NodeHelper.check(node)) {
            Fm.waring(t("FaCommand.Error.Interpreter.CantUnderstand"));
            return false;
        }
        return true;
    }

    @Override
    public void handle(FaIntf faIntf, Method method) {
        Intf annotation = method.getAnnotation(Intf.class);

        String node = annotation.node();

        // 检查
        if (!check(method, annotation.node())) return;

        // 检查节点是否合规
        faIntf.setMethod(method);
        faIntf.setNode(node);
    }
    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return Intf.class;
    }
}
