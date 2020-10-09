package com.zsw.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * 这是角色信息
 *
 * @author ZhangShaowei on 2017/10/19 16:41
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo implements Serializable {

    public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private static final long serialVersionUID = -6047159763784224187L;

    /**
     * token 所属的 client Id
     */
    private String clientId;

    /**
     * 登陆用户名
     */
    private String username;

    /**
     * 角色
     */
    private List<String> roles;

    /**
     * 附加属性，目前只有 Map
     */
    @JsonIgnore
    private transient Map<String, Object> attr;

    /**
     * 懒加载，如果不使用则不进行 io
     */
    @JsonIgnore
    private transient Supplier<Map<String, Object>> supplier;

    /**
     * @return Map<String, Object>
     */
    @Transient
    public Map<String, Object> getAttr() {
        return Optional.ofNullable(this.attr).orElseGet(() -> this.attr = supplier.get());
    }


}
