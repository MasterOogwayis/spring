package com.zsw.persistence.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
 * @author ZhangShaowei on 2021/8/26 10:26
 */
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
public class TestUser implements Serializable {

    private static final long serialVersionUID = -5168764260002876734L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 19)
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "firstName", nullable = false, length = 20)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 20)
    private String lastName;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "phone", nullable = false, unique = true, length = 15)
    private String phone;

    @CreatedBy
    @Column(name = "creator", nullable = false, updatable = false, length = 20)
    private String creator;

    @CreatedDate
    @Column(name = "createTime", nullable = false, updatable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    @LastModifiedBy
    @Column(name = "updator", length = 20)
    private String updator;

    @LastModifiedDate
    @Column(name = "updateTime")
    private LocalDateTime updateTime;


}
