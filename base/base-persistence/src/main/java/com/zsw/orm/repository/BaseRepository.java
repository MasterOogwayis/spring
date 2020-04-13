package com.zsw.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础功能直接继承这个接口就行，不需要写实现类 @Repository XXRepository extends BaseRepository
 * 若需要自定义功能比如使用 sql ,步骤如下：
 * 1. 创建接口 XXRepositoryCustom extends BaseCustomRepository
 * 2. 然后实现类 XXRepositoryCustomImpl implement XXRepositoryCustom，注意实现类必须为 interface + Impl 命名方式
 * 3. 最终 @Repository XXRepository extends BaseRepository, XXRepositoryCustom
 * 4. 使用 @Autowired XXRepository xxRepository 即可
 * <p>
 * 注：能使用 hql 语句的几乎都能使用注解实现，多使用 Optional 返回值的方法，大多直接返回类型都有可能抛出异常
 *
 * @author ZhangShaowei on 2017/9/8 14:48
 * @param <T> class
 * @param <ID> Long
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * 请使用 Optional 返回的实现
     *
     * @param id ID
     * @return
     */
    T get(ID id);

    void delete(ID id);

    void saveOrUpdate(T t);

}
