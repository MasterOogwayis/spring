package com.zsw.persistence.repository;
/**
 * @author ZhangShaowei on 2017/10/12 15:13
 */

import com.zsw.base.repository.BaseRepository;
import com.zsw.persistence.bean.SaleOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * SaleOrder
 *
 * @author ZhangShaowei on 2017/10/12 15:13
 **/
@Repository
@Qualifier("saleOrderRepository")
public interface SaleOrderRepository extends BaseRepository<SaleOrder, Long> {

}
