package com.zsw.orm.spring.jpa.generator;

import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2020/5/14 15:27
 */
@AllArgsConstructor
public class SnowflakeIdGenerator implements IdentifierGenerator {

    private IdWorker idWorker;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return idWorker.nextId();
    }
}
