package com.demo.spring.ioc.overview.domain;

import com.demo.spring.ioc.overview.annotation.Super;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/4/23 14:50
 */
@Super
@Getter
@Setter
@ToString(callSuper = true)
@Primary
@Component
public class SuperUser extends User {

    private String address;

}
