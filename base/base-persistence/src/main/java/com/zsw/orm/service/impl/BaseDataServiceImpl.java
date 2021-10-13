package com.zsw.orm.service.impl;

import com.zsw.orm.service.BaseDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 封装了一些常用方法
 *
 * @author ZhangShaowei on 2017/9/8 16:24
 */
@Slf4j
public abstract class BaseDataServiceImpl<T, E extends Serializable> implements BaseDataService<T, E> {

    /**
     * 实例 JpaRepository
     *
     * @return JpaRepository
     */
    public abstract JpaRepository<T, E> getRepository();


    /**
     * @param id id
     */
    @Override
    public void delete(E id) {
        this.getRepository().deleteById(id);
    }

    /**
     * @param entity entity
     */
    @Override
    public void delete(T entity) {
        this.getRepository().delete(entity);
    }

    /**
     * @param record new or old need to be updated
     * @return saved entity
     */
    @Override
    public T save(T record) {
        return this.getRepository().save(record);
    }

    /**
     * @param record new or old need to be updated
     * @return saved entity
     */
    @Override
    public T saveOrUpdate(T record) {
        return this.getRepository().save(record);
    }

    /**
     * @param id id
     * @return T
     */
    @Override
    public T get(E id) {
        return this.getRepository().getById(id);
    }

    /**
     * @param record T
     * @return Collection
     */
    @Override
    public List<T> findAll(T record) {
//        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
//                .withStringMatcher(StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
//                .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
//                .withMatcher("address", GenericPropertyMatchers.startsWith()) //地址采用“开始匹配”的方式查询
//                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
        return this.getRepository().findAll(Example.of(record));
    }

    /**
     * @param t     T
     * @param page  page
     * @param limit limit
     * @return page
     */
    @Override
    public Page<T> findAll(T t, int page, int limit) {
        return this.findAll(t, page, limit, null, null);
    }

    /**
     * 事实上并不常用，用hql吧，简单方便！
     * 分页方式也不常用
     *
     * @param t     T
     * @param page  page
     * @param limit pageSize
     * @return page
     */
    @Override
    public Page<T> findAll(T t, int page, int limit, String sortName, String sortType) {
        // 字符串模糊查询
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return this.getRepository().findAll(Example.of(t, matcher), buildPageRequest(page, limit, sortName, sortType));
    }

    /**
     * 创建分页请求.
     */
    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortName, String sortType) {
        if (StringUtils.hasText(sortName)) {
            return PageRequest.of(pageNumber - 1, pagzSize);
        }
        Sort sort = Sort.by(Sort.Direction.fromString(sortType), sortName);
        return PageRequest.of(pageNumber - 1, pagzSize, sort);
    }

}
