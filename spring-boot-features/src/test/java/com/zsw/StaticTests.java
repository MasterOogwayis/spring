package com.zsw;

import com.zsw.annotation.Wrapped;
import com.zsw.proxy.JdkProxy;
import com.zsw.service.TestThisService;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author ZhangShaowei on 2021/9/23 13:50
 */
public class StaticTests {

    public static void main(String[] args) {
//        TestThisService service = new JdkProxy().getInstance(new TestThisService());
//        System.out.println(AnnotationUtils.isCandidateClass(service.getClass(), Wrapped.class));
        System.out.println(Integer.valueOf("02"));
    }

}
