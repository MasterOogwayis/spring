package com;

import com.demo.Product;
import com.demo.ProductConverter;
import com.demo.ProductConverterImpl;
import com.demo.ProductDTO;

/**
 * @author ZhangShaowei on 2019/6/20 15:31
 **/
public class Test {

    public static void main(String[] args) {

        Product product = Product.builder().id(1L).name("水果").build();

        ProductConverter converter = new ProductConverterImpl();

        ProductDTO productDTO = converter.domain2dto(product);

        System.out.println(productDTO);

    }

}
