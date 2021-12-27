//package com.zsw.persistence.repository.custom.impl;
//
//import com.zsw.persistence.entity.TestUser;
//import com.zsw.persistence.repository.base.impl.BaseCustomRepositoryImpl;
//import com.zsw.persistence.repository.custom.TestUserRepositoryCustom;
//
//import javax.persistence.Query;
//import javax.persistence.Tuple;
//import java.util.List;
//
///**
// * @author ZhangShaowei on 2021/11/17 17:04
// */
//public class TestUserRepositoryCustomImpl extends BaseCustomRepositoryImpl<TestUser, Long>
//        implements TestUserRepositoryCustom {
//
//    static {
//        System.out.println(123);
//    }
//    @Override
//    public List<Tuple> findAvailable() {
//        String sql = "select * from t_user";
//        Query query = this.getEntityManager().createNativeQuery(sql, Tuple.class);
//        //noinspection unchecked
//        return query.getResultList();
//    }
//}
