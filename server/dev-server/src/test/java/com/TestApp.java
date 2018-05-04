package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * junit
 *
 * @author ZhangShaowei on 2018/3/22 14:02
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DevApplication.class)
//@AutoConfigureMockMvc
public class TestApp {

//    @Autowired
//    private WebApplicationContext context;
//
//    /**
//     * 模拟MVC对象
//     */
////    @Autowired
//    private MockMvc mvc;
//
//    /**
//     * @throws Exception
//     */
//    @Before
//    public void before() throws Exception {
//        mvc = MockMvcBuilders.webAppContextSetup(context).build(); //.apply(springSecurity())
//    }
//
//    /**
//     * @throws Exception
//     */
//    @After
//    public void after() throws Exception {
//    }
//
//    /**
//     * @throws Exception
//     */
////    @Test
//////    @WithMockUser 虚拟用户
//////    @WithUserDetails("superadmin")
////    public void testOriginList() throws Exception {
//////        MvcResult result =
////        mvc.perform(MockMvcRequestBuilders
////                .post("/productLine/originList/{version}", "version") //originList/{version}
////                .contentType(MediaType.APPLICATION_JSON)
//////                .param("name", "value") form 参数
//////                .content("这是数据流")
////                .accept(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
////                .andExpect(jsonPath("$.code").value(200));
//////                .andReturn();
//////        logger.info(result.getResponse().getContentAsString());
////    }
//
//    /**
//     * logger
//     */
//    private static final Logger logger = LoggerFactory.getLogger(TestApp.class);
//
//    private static final String ADMIN = "admin";
//
//    private final Gson gson = new GsonBuilder().create();
//
//    @Test
//    //@WithMockUser 虚拟用户
////    @WithUserDetails(ADMIN)
//    public void test() {
//        logger.debug("test");
//    }


}
