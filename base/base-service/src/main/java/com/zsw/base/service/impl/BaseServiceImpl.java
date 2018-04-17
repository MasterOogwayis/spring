package com.zsw.base.service.impl;

import com.zsw.base.bean.commons.BaseBean;
import com.zsw.base.service.BaseService;
import com.zsw.base.utils.JacksonSerializer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        return this.getRepository().findAll(Example.of(t), buildPageRequest(page, limit, null, null));
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
     * 属性拷贝
     *
     * @param source
     * @param target
     * @param ignoreProperties
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
