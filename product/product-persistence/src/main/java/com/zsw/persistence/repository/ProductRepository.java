package com.zsw.persistence.repository;

import com.zsw.base.repository.BaseRepository;
import com.zsw.persistence.bean.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangShaowei on 2017/10/12 11:18
 */
@Repository
@Qualifier("productRepository")
public interface ProductRepository extends BaseRepository<Product, Long> {


}
