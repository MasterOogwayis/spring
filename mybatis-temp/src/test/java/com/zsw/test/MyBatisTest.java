package com.zsw.test;

import com.MybatisTempApplication;
import com.zsw.persistence.dao.CustomerMapper;
import com.zsw.persistence.entity.Customer;
import com.zsw.persistence.entity.CustomerExample;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author ZhangShaowei on 2019/4/25 9:55
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisTempApplication.class)
public class MyBatisTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(CustomerMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession session = sqlSessionFactory.openSession();
        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);

        CustomerExample example = new CustomerExample();
        example.createCriteria().andIdEqualTo(1L);

        List<Customer> all = customerMapper.selectByExample(example);
        System.out.println(all);


//        CustomerExample example = new CustomerExample();
//        example.createCriteria().andIdEqualTo(1L);
//        List<Customer> customers = customerMapper.selectByExample(example);
//        System.out.println(customers);

    }


}
