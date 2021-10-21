package com.base.persistence.entity;

import lombok.Data;
import org.springframework.data.geo.Point;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2021/10/21 10:35
 */
@Data
public class Address implements Serializable {

    private static final long serialVersionUID = -8299925020647990571L;
    private final Point point;

    private String street;

    private String zipCode;

}
