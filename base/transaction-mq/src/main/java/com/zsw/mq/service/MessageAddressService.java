package com.zsw.mq.service;

import com.zsw.base.utils.JacksonSerializer;
import com.zsw.mq.base.BaseMqMessage;
import com.zsw.mq.persistence.domain.MessageAddress;
import com.zsw.mq.persistence.repository.MessageAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * MessageAddress Service
 *
 * @author ZhangShaowei on 2018/3/14 9:56
 **/
@Transactional(rollbackFor = Exception.class)
@Service
public class MessageAddressService extends JacksonSerializer {


    /**
     *
     */
    @Autowired
    private MessageAddressRepository messageAddressRepository;

    /**
     * @param key     address
     * @param message BaseMqMessage
     * @return
     */
    public void add(final String key, final BaseMqMessage message) {
        MessageAddress address = new MessageAddress();
        address.setAddress(key);
        address.setMessage(super.toJson(message));
        address.setCreateTimestamp(new Date());
        this.messageAddressRepository.saveOrUpdate(address);
    }

    /**
     * @param key address
     * @return
     */
    public MessageAddress getByAddress(String key) {
        return this.messageAddressRepository.getByAddress(key);
    }


    /**
     * @param messageAddress MessageAddress
     */
    public void delete(final MessageAddress messageAddress) {
        this.messageAddressRepository.delete(messageAddress);
    }

    /**
     * @param key address
     */
    public void delAddress(final String key) {
        MessageAddress messageAddress = this.messageAddressRepository.getByAddress(key);
        if (messageAddress != null) {
            this.messageAddressRepository.delete(messageAddress);
        }
    }

    /**
     * @param messageAddress MessageAddress
     * @return
     */
    public void saveOrUpdate(final MessageAddress messageAddress) {
        this.messageAddressRepository.saveOrUpdate(messageAddress);
    }

}
