package com.zsw.base.service.impl;

import com.zsw.base.service.BaseService;
import com.zsw.base.utils.JacksonSerializer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/9/8 16:24
 */

public abstract class BaseServiceImpl<T, E extends Serializable> extends JacksonSerializer implements BaseService<T, E> {

    /**
     * 实例 JpaRepository
     *
     * @return
     */
    public abstract JpaRepository<T, E> getRepository();

    /**
     *
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * @param id
     */
    @Override
    public void delete(E id) {
        this.getRepository().delete(id);
    }

    /**
     * @param entity
     */
    @Override
    public void delete(T entity) {
        this.getRepository().delete(entity);
    }

    /**
     * @param record
     * @return
     */
    @Override
    public T save(T record) {
        return this.getRepository().save(record);
    }

    /**
     * @param record
     * @return
     */
    @Override
    public T saveOrUpdate(T record) {
        return this.getRepository().save(record);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public T get(E id) {
        return this.getRepository().getOne(id);
    }

    /**
     * @param record
     * @return
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
     * @param t
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Page<T> findAll(T t, int page, int limit) {
        return this.findAll(t, page, limit, null, null);
    }

    /**
     * @param t
     * @param page
     * @param limit
     * @return
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
        if (StringUtils.isEmpty(sortName)) {
            return new PageRequest(pageNumber - 1, pagzSize);
        }
        Sort sort = new Sort(Sort.Direction.fromStringOrNull(sortType), sortName);
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }

    /**
     * 属性拷贝  目标属性会被源覆盖
     *
     * @param source           源
     * @param target           目标
     * @param ignoreProperties 忽略字段
     */
    protected void copyProperties(Object source, Object target, String... ignoreProperties) {
        BeanUtils.copyProperties(source, target, ignoreProperties);
    }

    /**
     * 只处理 BaseBean 子类
     * 通用注入创建 更新信息 可通过super调用
     *
     * @param record
     * @return
     */
//    private <R extends BaseBean> R addValue(R record) {
//        try {
//            Field[] fields = record.getClass().getDeclaredFields();
//            for (Field field : fields) {
//
//                if (Objects.nonNull(field.getAnnotation(Id.class))) {
//                    field.setAccessible(true);
//                    // 主键不为空 则为update
//                    if (Objects.nonNull(field.get(record))) {
//                        record.setUpdator(this.currentUser().getUsername());
//                        record.setUpdateTimestamp(new Date());
//                    } else {
//                        record.setCreator(this.currentUser().getUsername());
//                        record.setCreateTimestamp(new Date());
//                    }
//                    break;
//                }
//
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return record;
//    }

}
