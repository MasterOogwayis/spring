package com.zsw.persistence.repository.custom;

import com.zsw.persistence.entity.TestUser;
import com.zsw.persistence.repository.base.BaseCustomRepository;

import javax.persistence.Tuple;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/11/17 16:18
 */
public interface TestUserRepositoryCustom extends BaseCustomRepository<TestUser, Long> {

    List<Tuple> findAvailable();

}
