package com.zsw.persistence.timedtask.repository;

import com.zsw.persistence.timedtask.bean.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangShaowei on 2017/10/12 11:18
 */
@Repository
@Qualifier("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {


}
