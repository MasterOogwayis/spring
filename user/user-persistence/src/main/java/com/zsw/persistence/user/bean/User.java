package com.zsw.persistence.user.bean;


import com.zsw.base.bean.commons.BaseBean;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/9/12 13:35
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User extends BaseBean {

    private static final long serialVersionUID = -6630917103972003205L;


    /**
     *
     */
    @Column(name = "USER_NAME", nullable = false)
    private String username;

    /**
     *
     */
    @Column(name = "PASSWORD", nullable = false)
    private String password;


    /**
     * 角色
     */
//    @ManyToMany(cascade = CaJsonIgnorePropertiesscadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"),
            foreignKey = @ForeignKey(name = "user_role_ibfk_1"),
            inverseForeignKey = @ForeignKey(name = "user_role_ibfk_2"))
    private List<Role> roles;


}
