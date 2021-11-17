package com.zsw;

import com.zsw.persistence.repository.base.BaseCustomRepository;
import com.zsw.persistence.repository.base.impl.BaseCustomRepositoryImpl;
import org.junit.Test;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Method;

/**
 * @author ZhangShaowei on 2021/11/17 16:23
 */
public class JpaRepositoryTests {

    @Test
    public void test() {

        TestRepository repository = new TestRepository();
        repository.afterPropertiesSet();
        System.err.println(repository);

        Method[] methods = A.class.getMethods();
        System.out.println(methods);
        methods = A.class.getDeclaredMethods();
        System.err.println(methods);


    }


    class TestRepository extends BaseCustomRepositoryImpl<Long, Double> {

    }

    interface A extends BaseCustomRepository<Long, Double> {
        String hello();
    }


}
