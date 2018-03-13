package com.zsw.mq.service;

import com.zsw.base.service.BaseService;
import com.zsw.mq.BaseMqMessage;
import com.zsw.mq.persistence.bean.MessageAddress;
import com.zsw.mq.persistence.repository.MessageAddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional(rollbackFor = Exception.class)
@Service("messageAddressService")
public class MessageAddressService extends BaseService {

    /**
     *
     */
    //@Resource(name = MessageAddressHibernateDao.BEAN_NAME)
    @Autowired
    private MessageAddressDao messageAddressDao;


    public MessageAddress add(String key, BaseMqMessage message) {
        MessageAddress address = new MessageAddress();
        address.setAddress(key);
        address.setMessage(this.toJson(message));
        address.setCreateTimestamp(new Date());
        this.messageAddressDao.saveOrUpdate(address);
        return address;
    }

    public MessageAddress getByAddress(String key) {
        return this.messageAddressDao.getByAddress(key);
    }


    public void del(MessageAddress messageAddress) {
        this.messageAddressDao.delete(messageAddress);
    }

    /**
     * @param messageAddress MessageAddress
     * @return  */
    public MessageAddress saveOrUpdate(MessageAddress messageAddress) {
        messageAddress.setCreateTimestamp(new Date());
        return this.messageAddressDao.saveOrUpdate(messageAddress);
    }
}
