package com.zsw.persistence.repository.custom;

import com.anze.base.persistence.repository.BaseCustomRepository;
import com.zsw.persistence.entity.IcbcOrder;

import java.util.List;

/**
 * @author ZhangShaowei on 2019/11/14 14:46
 **/
public interface IcbcOrderRepositoryCustom extends BaseCustomRepository<IcbcOrder, Long> {

    List<IcbcOrder> t(Long amount);

    List<IcbcOrder> findByParams(String hql, Object... values);

}
