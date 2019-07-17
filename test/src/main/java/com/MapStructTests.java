package com;

import com.demo.Product;
import com.demo.ProductDTO;
import com.demo.ProductMapper;
import org.junit.Test;

/**
 * @author ZhangShaowei on 2019/6/20 15:31
 **/
public class MapStructTests {

    @Test
    public void test() {

        Product product = Product.builder().id(1L).name("水果").build();

        ProductDTO productDTO = ProductMapper.INSTANCE.toDto(product);

        System.out.println(productDTO);

    }

}
