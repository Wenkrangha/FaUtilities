package com.wenkrang.faClip.Module.FaInterface;

import com.wenkrang.faClip.Module.FaInterface.Annotaion.Intf;
import com.wenkrang.faClip.Module.FaInterface.AnnotationHandler.SimpleAnnotationHandler;
import com.wenkrang.faClip.Module.FaInterface.AnnotationHandler.intfHandler;
import com.wenkrang.faClip.Module.FaInterface.FaParam.FaParam;
import com.wenkrang.faClip.Module.FaInterface.FaParam.SimpleParam;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.wenkrang.faClip.Module.FaMessage.Helper.i18nHelper.ft;

public class FaIntfInterpreter {
    public FaInterfaceInstance interfaceInstance;

    public ArrayList<SimpleAnnotationHandler> annotationHandlers = new ArrayList<>();

    private final FaParam faParam;

    public FaIntfInterpreter(FaInterfaceInstance interfaceInstance) {
        this.interfaceInstance = interfaceInstance;
        faParam = new FaParam();

        annotationHandlers.add(new intfHandler());
    }

    public @Nullable FaIntf interpret(Method method, String node) {
        // 检查是否静态
        if (Modifier.isStatic(method.getModifiers())) {
            // 初始化接口
            FaIntf faIntf = new FaIntf(interfaceInstance);

            // 检查接口是否合规
            if (!intfHandler.check(method, node)) return null;

            faIntf.setMethod(method);
            faIntf.setNode(node);

            annotationHandlers.stream()
                    .filter(i -> method.isAnnotationPresent(i.getAnnotationClass()))
                    .forEach(i -> i.handle(faIntf, method));

            // 委托转换器
            Class<?>[] parameterTypes = method.getParameterTypes();

            faIntf.paramConvertors = new SimpleParam[method.getParameterCount()];

            for (int i = 0;i < parameterTypes.length;i++) {
                Type parameterType = parameterTypes[i];

                SimpleParam convertor = faParam.getConvertor(parameterType);

                faIntf.paramConvertors[i] = convertor;
            }

            return faIntf;
        } else {
            throw new RuntimeException(ft("FaInterface.Error.Interpreter.NotStatic", method.getName()));
        }
    }

    public FaIntf interpret(Method method) {
        Intf annotation = method.getAnnotation(Intf.class);

        if (annotation != null) {
            return interpret(method, annotation.value());
        }

        return null;
    }

    public FaParam getFaParam() {
        return faParam;
    }
}
