package com.zsw.mq.persistence.repository.impl;

import com.zsw.base.repository.jpa.BaseJpaRepository;
import com.zsw.mq.persistence.bean.MessageAddress;
import com.zsw.mq.persistence.repository.MessageAddressDao;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


/**
 * MessageAddress
 *
 * @author ZhangShaowei on 2018/3/8 14:14
 **/
//@Repository(MessageAddressHibernateDao.BEAN_NAME)
public class MessageAddressHibernateDao extends BaseJpaRepository<MessageAddress, Long> implements MessageAddressDao {

    /**
     *
     */
    public static final String BEAN_NAME = "messageAddressHibernateDao";

    public MessageAddressHibernateDao(Class<MessageAddress> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
    }


    @Override
    public MessageAddress getByAddress(String key) {
        String hql = "from MessageAddress where address = :address";
        Query query = this.getEntityManager().createQuery(hql);
        query.setParameter("address", key);
        //noinspection unchecked
        List<MessageAddress> addresses = query.getResultList();
        return CollectionUtils.isEmpty(addresses) ? null : addresses.get(0);
    }
}
