package com.zsw.mq.persistence.repository;


import com.zsw.base.repository.BaseRepository;
import com.zsw.mq.persistence.domain.MessageAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * MessageAddressRepository
 *
 * @author ZhangShaowei on 2018/3/8 14:13
 **/
@Repository
public interface MessageAddressRepository extends BaseRepository<MessageAddress, Long> {

    /**
     * @param key address
     * @return
     */
    @Query("from MessageAddress where address = :key")
    MessageAddress getByAddress(@Param("key") String key);

}
