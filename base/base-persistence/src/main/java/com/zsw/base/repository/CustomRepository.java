package com.zsw.base.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * CustomRepository
 *
 * @author ZhangShaowei on 2017/9/8 14:57
 **/
@NoRepositoryBean
public interface CustomRepository<T> {

    /**
     * delete
     *
     * @param entity T
     */
    void delete(T entity);

    /**
     * 删除所有
     *
     * @param entities List<T>
     */
    void deleteAll(Collection<T> entities);

    /**
     * getOne
     *
     * @param id Long
     * @return T
     */
    T get(Long id);

    /**
     * findOne
     *
     * @param id
     * @return
     */
    T findOne(Long id);

    /**
     * @param hqlStr hsql
     * @return list
     */
    List<T> find(String hqlStr);

    /**
     * @param hqlStr     hql
     * @param maxResults maxSize
     * @return list
     */
    List<T> find(String hqlStr, int maxResults);

    /**
     * @param hqlStr      hql
     * @param firstResult first
     * @param maxResults  maxSize
     * @return list
     */
    List<T> find(String hqlStr, int firstResult, int maxResults);

    /**
     * 通过hql执行查询，通过参数绑定“?”的方式查询
     *
     * @param hqlStr hql
     * @param values params
     * @return list
     */
    List<T> findByParam(String hqlStr, Object... values);

    /**
     * 通过hql执行查询，通过参数绑定“?”的方式查询
     *
     * @param hqlStr     hql
     * @param maxResults maxSize
     * @param values     params
     * @return list
     */
    List<T> findByParam(String hqlStr, int maxResults, Object... values);

    /**
     * 通过hql执行查询，通过参数绑定“?”的方式查询
     *
     * @param hqlStr      hql
     * @param firstResult first
     * @param maxResults  maxSize
     * @param values      params
     * @return list
     */
    List<T> findByParam(String hqlStr, int firstResult, int maxResults, Object... values);

    /**
     * 通过参数绑定:name方式查询
     *
     * @param hqlStr     hql
     * @param paramName  param name
     * @param paramValue param value
     * @return list
     */
    List<T> findByNamedParam(String hqlStr, String paramName, Object paramValue);

    /**
     * 描述：通过参数绑定:name方式查询
     *
     * @param hqlStr     hql
     * @param maxResults 最大查询的条数，大于0的整数，否则忽略
     * @param paramName  绑定:name的name
     * @param paramValue 绑定:name的value
     * @return 对象集合
     */
    List<T> findByNamedParam(String hqlStr, int maxResults, String paramName, Object paramValue);

    /**
     * 描述：通过参数绑定:name方式查询<br>
     *
     * @param hqlStr      hql
     * @param firstResult 开始查询的行数，大于或等于0的整数，否则忽略
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param paramName   绑定:name的name
     * @param paramValue  绑定:name的value
     * @return 对象集合
     */
    List<T> findByNamedParam(String hqlStr, int firstResult, int maxResults, String paramName, Object paramValue);

    /**
     * 描述：通过参数绑定:name方式查询
     *
     * @param hqlStr      hql
     * @param paramNames  绑定:name的names
     * @param paramValues 绑定:name的values
     * @return 对象集合
     */
    List<T> findByNamedParam(String hqlStr, String[] paramNames, Object[] paramValues);

    /**
     * 描述：通过参数绑定:name方式查询
     *
     * @param hqlStr      hql
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param paramNames  绑定:name的names
     * @param paramValues 绑定:name的values
     * @return 对象集合
     */
    List<T> findByNamedParam(String hqlStr, int maxResults, String[] paramNames,
                             Object[] paramValues);

    /**
     * 描述：通过参数绑定:name方式查询<br>
     * <span style="color: red;">&lt;勿忘国耻，纪念9·18&gt<br>&lt;钓岛要收复，反日要理性&gt;</span>
     *
     * @param hqlStr      hql
     * @param firstResult 开始查询的行数，大于或等于0的整数，否则忽略
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param paramNames  绑定:name的names
     * @param paramValues 绑定:name的values
     * @return 对象集合
     */
    List<T> findByNamedParam(String hqlStr, int firstResult,
                             int maxResults, String[] paramNames,
                             Object[] paramValues);

    /**
     * 描述：通过参数绑定:name方式查询
     *
     * @param hqlStr      hql
     * @param namedParams name，value映射
     * @return 对象集合
     */
    List<T> findByNamedParam(String hqlStr, Map<String, Object> namedParams);

    /**
     * 描述：通过参数绑定:name方式查询
     *
     * @param hqlStr      hql
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param namedParams name，value映射
     * @return 对象集合
     */
    List<T> findByNamedParam(String hqlStr, int maxResults,
                             Map<String, Object> namedParams);

    /**
     * 描述：通过参数绑定:name方式查询<br>
     * <span style="color: red;">&lt;勿忘国耻，纪念9·18&gt<br>&lt;钓岛要收复，反日要理性&gt;</span>
     *
     * @param hqlStr      hql
     * @param firstResult 开始查询的行数，大于或等于0的整数，否则忽略
     * @param maxResults  最大查询的条数，大于0的整数，否则忽略
     * @param namedParams name，value映射
     * @return 对象集合
     */
    List<T> findByNamedParam(String hqlStr, int firstResult,
                             int maxResults, Map<String, Object> namedParams);

}
