package com.zsw.mq.persistence.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 消息暂存地址
 *
 * @author ZhangShaowei on 2018/3/8 14:08
 **/
@Entity
@Table(name = "MESSAGE_ADDRESS", indexes = {@Index(name = "ADDRESS", columnList = "ADDRESS")})
public class MessageAddress implements Serializable {

    private static final long serialVersionUID = -7456190903411207874L;


    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    /**
     * 消息地址  redis key
     */
    @Column(name = "ADDRESS", nullable = false)
    private String address;

    /**
     * 消息体 BaseMqMessage to json
     */
    @Column(name = "MESSAGE", nullable = false)
    private String message;

    /**
     *
     */
    @Column(
            name = "CREATE_TIMESTAMP",
            nullable = false,
            updatable = false,
            columnDefinition = "datetime DEFAULT CURRENT_TIMESTAMP"
    )
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
