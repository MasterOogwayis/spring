package com.demo.spring.dependency.domain;

import com.demo.spring.ioc.overview.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ZhangShaowei on 2021/4/29 14:12
 */
@Getter
@Setter
@ToString
public class UserHandler {

    private User superUser;

}
