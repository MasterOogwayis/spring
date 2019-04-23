package com.zsw.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Shaowei Zhang on 2019/4/24 0:25
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 4687880422396813080L;

    @Id
    @Column
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

}
