package com.zsw.pojo.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Singular;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：用户
 *
 * @author ZhangShaowei
 * @version 2.0.0
 * @since 2.0.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    private static final long serialVersionUID = 3065074931882037843L;
    /**
     * 登录名称
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户状态，0：停用，1：启用，2：冻结，9：注销
     */
    private Integer status;


    /**
     * 用户角色(权限)
     */
    @Singular
    private List<String> roles;

}
