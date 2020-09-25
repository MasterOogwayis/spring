package com.zsw;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * JPA 会自动建表
 * 消息暂存地址 保证事务提交后消息同步存储 发送成功后删除
 *
 * @author ZhangShaowei on 2018/3/8 14:08
 **/
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_user")
public class User implements Serializable {


    private static final long serialVersionUID = -6480480964525045376L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    /**
     * name
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 消息体 BaseMqMessage to json
     */
    @Column(name = "age", nullable = false)
    private Integer age;

    /**
     * name
     */
    @CreatedBy
    @Column(name = "creator", nullable = false, updatable = false)
    private String creator;

    /**
     *
     */
    @CreatedDate
    @Column(name = "createTime", updatable = false)
    private LocalDateTime createTime;

    /**
     * name
     */
    @LastModifiedBy
    @Column(name = "updator")
    private String updator;

    /**
     *
     */
    @LastModifiedDate
    @Column(name = "updateTime")
    private LocalDateTime updateTime;

}
