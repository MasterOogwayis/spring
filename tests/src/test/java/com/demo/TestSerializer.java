package com.demo;

import com.zsw.pojo.ProductDto;
import com.zsw.utils.JavaSerializer;

/**
 * @author ZhangShaowei on 2022/1/24 16:30
 */
public class TestSerializer {


    public static void main(String[] args) {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);

        System.out.println(productDto);

        JavaSerializer javaSerializer = new JavaSerializer();
        byte[] serialize = javaSerializer.serialize(productDto);

        Object obj = javaSerializer.deserialize(serialize);

        if (obj instanceof ProductDto) {
            System.out.println(obj);
        }
    }
}
