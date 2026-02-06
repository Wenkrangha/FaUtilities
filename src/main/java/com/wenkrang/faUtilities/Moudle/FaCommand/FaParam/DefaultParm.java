package com.wenkrang.faUtilities.Moudle.FaCommand.FaParam;

/**
 * 默认参数类，用于封装参数的类型和默认值
 * @param <T> 参数的类型
 */
public class DefaultParm<T> {
    private final Class<T> type;
    private T value;

    /**
     * 构造函数，初始化参数类型和默认值
     * @param type 参数的类型
     * @param value 参数的默认值
     */
    public DefaultParm(Class<T> type, T value) {
        this.type = type;
        this.value = value;
    }

    /**
     * 获取参数类型
     * @return 参数的类型
     */
    public Class<T> getType() {
        return type;
    }

    /**
     * 获取参数默认值
     * @return 参数的默认值
     */
    public T getValue() {
        return value;
    }

    /**
     * 设置参数默认值
     * @param value 新的默认值
     */
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DefaultParm{" +
                "type=" + type.getSimpleName() +
                ", value=" + value +
                '}';
    }
}
