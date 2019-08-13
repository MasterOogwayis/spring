package com;

/**
 * @author ZhangShaowei on 2019/8/1 15:32
 **/
//public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
//
//    @Autowired
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Long countByAge(Integer age) {
//        String hql = "select count(c) from Customer c where c.age = :age";
//        Query query = this.entityManager.createQuery(hql);
//        query.setParameter("age", age);
//        Number singleResult = (Number) query.getSingleResult();
//        return singleResult.longValue();
//    }
//}
