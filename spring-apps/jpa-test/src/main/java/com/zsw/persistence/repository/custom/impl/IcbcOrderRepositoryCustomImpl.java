package com.zsw.persistence.repository.custom.impl;

import com.anze.base.persistence.repository.impl.BaseCustomRepositoryImpl;
import com.zsw.persistence.entity.IcbcOrder;
import com.zsw.persistence.repository.custom.IcbcOrderRepositoryCustom;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author ZhangShaowei on 2019/11/14 14:46
 **/
public class IcbcOrderRepositoryCustomImpl extends BaseCustomRepositoryImpl<IcbcOrder, Long> implements IcbcOrderRepositoryCustom {


    @Override
    public List<IcbcOrder> t(Long amount) {
        String hql = "from IcbcOrder where amount = :amount";
        TypedQuery<IcbcOrder> query = this.createQuery(hql);
        query.setParameter("amount", amount);
        return query.getResultList();
    }

    @Override
    public List<IcbcOrder> findByParams(String hql, Object... values) {
        TypedQuery<IcbcOrder> query = this.createQuery(hql);
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        return query.getResultList();
    }
}
