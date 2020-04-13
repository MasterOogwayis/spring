package com.zsw.orm.redis.service;

import com.zsw.orm.redis.configuration.CacheConstsCustom;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

/**
 * @author ZhangShaowei on 2017/8/28 17:21
 */

public class ExampleService {

    /**
     * key : 支持EL表达式
     *
     * @return
     */
    @Cacheable(value = "proxy", key = "'Ex:' + #id", condition = "#id != null")
    public Ex get(final Long id){
        return new Ex();
    }


    /**
     * 每次执行该方法都将结果放进redis
     *
     * @param ex
     */
    @CachePut(value = "proxy", key = "'Ex:' + #ex.getId()", condition = "#ex != null")
    public Ex save(Ex ex) {
        return ex;
    }


    /**
     * 清空某个缓存
     *
     * @param ex
     */
    @CacheEvict(value = "proxy", key = "'Ex' + #ex.getId()", condition = "#ex != null")
    public void delete(Ex ex){

    }

    /**
     * @return
     */
    @Caching(
            cacheable = {@Cacheable(value = "proxy", key = "'Ex:' + #id", condition = "#id != null")},
            put = {@CachePut(value = "proxy", key = "'Ex:' + #ex.getId()", condition = "#ex != null")},
            evict = {@CacheEvict(value = "proxy", key = "'Ex' + #ex.getId()", condition = "#ex != null")}
    )
    public Ex all(){
        return new Ex();
    }

    @Cacheable(value = CacheConstsCustom.UserKeys.KEY_USER_ROLES, key = "#userId", condition = "#userId != null")
    public Object getRoles(Long userId) {
        return "roles";
    }

    private class Ex {

        private Long id;

        /**  */
        public Long getId() {
            return id;
        }

        /**  */
        public void setId(Long id) {
            this.id = id;
        }
    }

}
