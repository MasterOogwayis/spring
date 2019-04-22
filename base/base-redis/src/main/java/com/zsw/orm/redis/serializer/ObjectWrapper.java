package com.zsw.orm.redis.serializer;

/**
 * redis序列化辅助类.单纯的泛型无法定义通用schema，原因是无法通过泛型T得到Class<T>
 *
 * @author ZhangShaowei on 2017/9/13 11:11
 */

public class ObjectWrapper {
    /**
     *
     */
    private Object object;

    /**
     * @param object
     */
    public ObjectWrapper(Object object) {
        super();
        this.object = object;
    }

    /**
     *
     */
    public ObjectWrapper() {
        super();
    }

    /**
     * @return
     */
    public Object getObject() {
        return object;
    }

    /**
     * @param object
     */
    public void setObject(Object object) {
        this.object = object;
    }
}
