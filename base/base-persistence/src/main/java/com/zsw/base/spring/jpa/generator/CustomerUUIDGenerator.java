package com.zsw.base.spring.jpa.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * Custom UUID Generator
 *
 * @author ZhangShaowei on 2018/3/23 13:54
 **/
public class CustomerUUIDGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
