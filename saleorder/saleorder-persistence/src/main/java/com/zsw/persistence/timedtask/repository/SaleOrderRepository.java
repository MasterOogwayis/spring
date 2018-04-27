package com.zsw.persistence.timedtask.repository;
/**
 * @author ZhangShaowei on 2017/10/12 15:13
 */

import com.zsw.persistence.timedtask.bean.SaleOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * SaleOrder
 *
 * @author ZhangShaowei on 2017/10/12 15:13
 **/
@Repository
@Qualifier("saleOrderRepository")
public interface SaleOrderRepository extends JpaRepository<SaleOrder, Long> {

}
