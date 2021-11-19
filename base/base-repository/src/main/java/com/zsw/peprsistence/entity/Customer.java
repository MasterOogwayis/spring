package com.zsw.peprsistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ZhangShaowei on 2021/11/18 10:01
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "t_customer")
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = -5094459750439732785L;
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 19)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "idNo", nullable = false, length = 20)
    private String idNo;

    @Column(name = "age", nullable = false, length = 2)
    private Integer age;

    /**
     * name
     */
    @CreatedBy
    @Column(name = "creator", nullable = false, updatable = false, length = 20)
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
    @Column(name = "updator", length = 50)
    private String updator;

    /**
     *
     */
    @LastModifiedDate
    @Column(name = "updateTime")
    private LocalDateTime updateTime;

}
