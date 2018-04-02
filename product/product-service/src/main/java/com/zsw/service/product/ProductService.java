package com.zsw.service.product;

import com.zsw.persistence.bean.Product;
import com.zsw.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangShaowei on 2017/10/12 11:21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

    /**
     *
     */
    @Autowired
    private ProductRepository productRepository;


    /**
     * @param product
     * @return
     */
    public Product saveOrUpdate(Product product) {
        return this.productRepository.save(product);
    }


    /**
     * @param id id
     * @return Product
     */
    public Product get(final Long id) {
        return this.productRepository.findOne(id);
    }

}
