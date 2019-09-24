package com.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * ProductService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>07/17/2019</pre>
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DevApplication.class)
@RunWith(Parameterized.class)
public class ProductServiceTest {

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[2][0]; // repeat count which you want
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: get(Long id)
     * {@link ProductService#get}
     */
    @Test
    public void testGet() throws Exception {
        //TODO: Test goes here...
    }


} 
