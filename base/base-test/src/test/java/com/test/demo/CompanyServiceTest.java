package com.test.demo;

import com.TestApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * CompanyService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>06/21/2019</pre>
 */
@Rollback(true)
@RunWith(SpringRunner.class)
@Import(JunitConfiguration.class)
@SpringBootTest(classes = TestApplication.class)
public class CompanyServiceTest {

    /**
     * //     *
     * //
     */
    @Autowired
    private WebApplicationContext context;

//    @Mock
//    private DoSomeThingService doSomeThingService;
//
//    /**
//     *
//     */
//    private MockMvc mvc;
//
//    @Before
//    public void before() throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }

    //    @Test
////    @WithMockUser
////    @WithUserDetails("superadmin")
//    public void testOriginList() throws Exception {
////        MvcResult result =
//        mvc.perform(MockMvcRequestBuilders
//                .post("/productLine/originList/{version}", "version")
//                .contentType(MediaType.APPLICATION_JSON)
////                .param("name", "value") form params
////                .content("data")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.code").value(200));
////                .andReturn();
////        logger.info(result.getResponse().getContentAsString());
//    }
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: doSomething(String work)
     */
    @Test
    public void testDoSomething() throws Exception {
        DoSomeThingService bean = context.getBean(DoSomeThingService.class);
        String s = bean.get("123");
        System.out.println(s);
    }


} 
