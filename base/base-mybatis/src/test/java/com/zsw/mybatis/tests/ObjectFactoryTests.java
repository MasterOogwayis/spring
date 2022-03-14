package com.zsw.mybatis.tests;

import com.zsw.persistence.entity.SaleOrder;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

/**
 * @author Shaowei Zhang on 2022/3/11 22:43
 */
public class ObjectFactoryTests {

    public static void main(String[] args) {
        DefaultObjectFactory objectFactory = new DefaultObjectFactory();
        SaleOrder saleOrder = objectFactory.create(SaleOrder.class);

        System.out.println(saleOrder);
    }

}
