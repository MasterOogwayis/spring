package com.zsw.api.security;

import com.zsw.persistence.bean.UserRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/11/10 14:28
 */
@RestController
public class SecurityController {


    /**
     * @param id id
     * @return UserRole
     */
    @GetMapping("get")
    @PreAuthorize("hasPermission(principal, 'APPLY_AUDIT_DENY')")
    public UserRole get(Long id) {
        return null;
    }


}
