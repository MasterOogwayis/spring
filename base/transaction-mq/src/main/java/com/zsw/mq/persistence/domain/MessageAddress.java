package com.zsw.mq.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * JPA 会自动建表
 * 消息暂存地址 保证事务提交后消息同步存储 发送成功后删除
 *
 * @author ZhangShaowei on 2018/3/8 14:08
 **/
@Entity
@Table(name = "MESSAGE_ADDRESS")
public class MessageAddress implements Serializable {

    private static final long serialVersionUID = -7456190903411207874L;


    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    /**
     * 消息地址  redis key
     */
    @Column(name = "ADDRESS", nullable = false, unique = true)
    private String address;

    /**
     * 消息体 BaseMqMessage to json
     */
    @Column(name = "MESSAGE", nullable = false)
    private String message;

    /**
     *
     */
    @Column(name = "CREATE_TIMESTAMP", nullable = false, updatable = false)
    private Date createTimestamp;

    /**  */
    public Long getId() {
        return id;
    }

    /**  */
    public void setId(Long id) {
        this.id = id;
    }

    /**  */
    public String getAddress() {
        return address;
    }

    /**  */
    public void setAddress(String address) {
        this.address = address;
    }

    /**  */
    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    /**  */
    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    /**  */
    public String getMessage() {
        return message;
    }

    /**  */
    public void setMessage(String message) {
        this.message = message;
    }
}
