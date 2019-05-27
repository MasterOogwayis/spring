package com.zsw.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.zsw.persistence.dao.CustomerMapper;
import com.zsw.persistence.entity.Customer;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.loader.cglib.CglibProxyFactory;
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
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author ZhangShaowei on 2019/4/25 9:55
 **/
@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BaseMyBatisApplication.class)
public class MyBatisTests {

//    @Autowired
//    private DataSource dataSource;

    /**
     * @throws Exception e
     */
    @Test
    public void test() throws Exception {


        // 1. 从 XML 中构建 SqlSessionFactory
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = new ClassPathResource(resource).getInputStream();
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession session = sqlSessionFactory.openSession();
//        CustomerMapper mapper = session.getMapper(CustomerMapper.class);
//        System.out.println(mapper.findAll());


        String url = "jdbc:mysql://localhost/mybatis";
        String username = "root";
        String password = "root";


        // 2. 不使用 XML 构建 SqlSessionFactory
//        DataSource dataSource = new PooledDataSource("", "", null);
        // Environment 2个关键属性 dataSource
        // c3p0
//        C3P0PooledDataSource dataSource = new C3P0PooledDataSource();
//        dataSource.setJdbcUrl(url);
//        dataSource.setUser(username);
//        dataSource.setPassword(password);
        // druid
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
        DataSource dataSource = new DriverManagerDataSource(url, username, password);
        // Environment 2个关键属性 transactionFactory
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        // 类型转换 预设 TypeHandlerRegistry
        configuration.getTypeHandlerRegistry().register("com.zsw.mybatis.typehandler");
        configuration.getTypeAliasRegistry().registerAliases("com.zsw.mybatis.typehandler");
        // mybatis 预设 TypeAliasRegistry
        configuration.getTypeAliasRegistry().registerAliases("com.zsw.persistence.entity");

//        configuration.setCacheEnabled(true);
        configuration.setLazyLoadingEnabled(true);
        configuration.setAggressiveLazyLoading(false);
        configuration.setLazyLoadTriggerMethods(Collections.emptySet());
        // 只有使用 cglib 代理模式才能使用延迟加载
        configuration.setProxyFactory(new CglibProxyFactory());
//        configuration.addMapper(CustomerMapper.class);
//        configuration.addMapper(UserMapper.class);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        // 插件
//        configuration.addInterceptor(new CustomerInterceptor());

        // PageHelper
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
//        properties.setProperty("helperDialect", "mysql");
        // 自动识别方言
        properties.setProperty("autoRuntimeDialect", "true");
        // pageSize=0 则查询全部
        properties.setProperty("pageSizeZero", "true");
        pageInterceptor.setProperties(properties);
//        configuration.addInterceptor(pageInterceptor);

        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
        sessionFactoryBean.setMapperLocations(resources);
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfiguration(configuration);
        SqlSessionFactory sessionFactory = sessionFactoryBean.getObject();
        // openSession 的时候 Executor 会根据插件 被代理，例如 PageInterceptor 被 Plugin 代理
        @Cleanup SqlSession session = sessionFactory.openSession();
//        Customer o = session.selectOne("com.zsw.persistence.dao.CustomerMapper.get", 1L);

        // Mapper 接口都会使用 MapperProxy 代理实现
        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);

        // jdk 1.7 及以下
//        Page<Object> page = PageHelper.startPage(1, 10);
//        List<Customer> all = customerMapper.findAll();
        // jdk 1.8
//        Page<Customer> page = PageHelper.startPage(1, 10).doSelectPage(customerMapper::findAll);
//        PageInfo<Customer> pageInfo = new PageInfo<>(page);
//        PageInfo<Customer> pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(customerMapper::findAll);
        Page<Customer> page = PageHelper.startPage(1, 10);
        PageInfo<Customer> pageInfo = new PageInfo<>(page);
        List<Customer> list = customerMapper.findAll();
//        List<Customer> all = pageInfo.getList();

        System.out.println(list);
//        System.out.println(all);
//        System.err.println(pageInfo);

//        Customer customer = customerMapper.get(1L);
//
//        System.out.println(1);
//
//        System.err.println(customer.getUser());

//        List<Customer> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(Customer.builder().age(i + 1).name("customer" + i).createDate(new Date()).build());
//        }
//
//        int i = customerMapper.batchInsert(list);
//        System.err.println(i);


//        BlobMapper blobMapper = session.getMapper(BlobMapper.class);
//        Blob blob = new Blob();
//        blob.setContent("content");
//        blob.setTitle("title");
//        Comment comment = new Comment();
//        comment.setAuthor("zsw");
//        comment.setContext("comment context");
//        comment.setCreateDate(new Date());
//        blob.setComment(comment);
//        blobMapper.insert(blob);

//        Blob blob1 = blobMapper.selectByPrimaryKey(1L);
//        System.err.println(blob1);

    }


}
