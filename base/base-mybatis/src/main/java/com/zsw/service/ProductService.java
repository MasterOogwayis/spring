package com.zsw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsw.persistence.entity.Product;
import com.zsw.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2020/11/2 11:17
 */
@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> implements IService<Product> {
}
