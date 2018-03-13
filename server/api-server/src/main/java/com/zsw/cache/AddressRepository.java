package com.zsw.cache;

import com.zsw.base.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * demo
 *
 * @author ZhangShaowei on 2018/3/8 13:58
 **/
@Repository
@Qualifier("addressRepository")
public interface AddressRepository extends BaseRepository<Address, Long> {
}
