package com.zsw.design.singleton.innerclass;

/**
 * @author ZhangShaowei on 2018/10/26 12:43
 **/
public class ObjectBean {

    public static ObjectBean getInstance() {
        return Creator.INSTANCE;
    }


    private static class  Creator {
        public static final ObjectBean INSTANCE = new ObjectBean();
    }

}
