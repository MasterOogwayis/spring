package com.zsw.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.zsw.persistence.dao.CustomerMapper;
import com.zsw.persistence.dao.UserMapper;
import com.zsw.persistence.entity.Customer;
import com.zsw.persistence.entity.CustomerExample;
import com.zsw.persistence.entity.User;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author ZhangShaowei on 2019/4/25 9:55
 **/
@Slf4j
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
        configuration.getTypeAliasRegistry().registerAliases("com.zsw.persistence.entity");

        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
//        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("autoRuntimeDialect", "true");
        pageInterceptor.setProperties(properties);
        configuration.addInterceptor(pageInterceptor);
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

//        Customer o = session.selectOne("com.zsw.persistence.dao.CustomerMapper.get", 1L);

        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
//        Customer customer = customerMapper.get(1L);

        PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(customerMapper::findAll);

        System.out.println(pageInfo);

    }


}
