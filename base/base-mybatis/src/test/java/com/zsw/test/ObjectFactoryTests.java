package com.zsw.test;

import com.zsw.persistence.entity.Product;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.junit.Test;
import org.apache.ibatis.reflection.factory.ObjectFactory;

/**
 * @author ZhangShaowei on 2020/5/7 16:26
 */
public class ObjectFactoryTests {


    @Test
    public void test() {
        ObjectFactory objectFactory = new DefaultObjectFactory();
        Product product = objectFactory.create(Product.class);

        System.out.println(product);
    }

}
