package com.zsw.cache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * demo
 *
 * @author ZhangShaowei on 2018/3/8 13:58
 **/
@Repository
@Qualifier("addressRepository")
public interface AddressRepository extends JpaRepository<Address, Long> {
}
