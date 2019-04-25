package com.zsw.test;

import com.zsw.mybatis.plugin.CustomerInterceptor;
import com.zsw.persistence.entity.Customer;
import lombok.Cleanup;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author ZhangShaowei on 2019/4/25 9:55
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BaseMyBatisApplication.class)
public class SimpleTest {

//    @Autowired
//    private DataSource dataSource;

    @Test
    public void test() throws Exception {


        // 1. 从 XML 中构建 SqlSessionFactory
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = new ClassPathResource(resource).getInputStream();
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession session = sqlSessionFactory.openSession();
//        CustomerMapper mapper = session.getMapper(CustomerMapper.class);
//        System.out.println(mapper.findAll());


        // 2. 不使用 XML 构建 SqlSessionFactory
//        DataSource dataSource = new PooledDataSource("", "", null);
        DataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost/mybatis", "root", "root");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        // 类型转换
//        configuration.getTypeHandlerRegistry().register("com.zsw.mybatis.typehandler");
//        configuration.getTypeAliasRegistry().registerAliases("com.zsw.mybatis.typehandler");
//        configuration.addMapper(CustomerMapper.class);
//        configuration.addMapper(UserMapper.class);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        // 插件
//        configuration.addInterceptor(new CustomerInterceptor());
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
        sessionFactoryBean.setMapperLocations(resources);
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfiguration(configuration);
        SqlSessionFactory sessionFactory = sessionFactoryBean.getObject();

        @Cleanup SqlSession session = sessionFactory.openSession();

        Customer o = session.selectOne("com.zsw.persistence.dao.CustomerMapper.get", 1L);

//        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
        System.out.println(o);

    }


}
