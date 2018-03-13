package com.zsw.mq.persistence.repository;


import com.zsw.base.repository.BaseRepository;
import com.zsw.mq.persistence.bean.MessageAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * MessageAddressDao
 *
 * @author ZhangShaowei on 2018/3/8 14:13
 **/
public interface MessageAddressDao extends BaseRepository<MessageAddress, Long> {

    /**
     * @param key String
     * @return
     */
    @Query("from MessageAddress where address = :address")
    MessageAddress getByAddress(@Param("address") String key);

}
