package com.api;

import com.DevApplication;
import com.config.DevTestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * TestController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>五月 15, 2018</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevApplication.class)
@Import(DevTestConfiguration.class)
public class TestControllerTest {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(TestControllerTest.class);

    /**
     *
     */
    @Autowired
    private WebApplicationContext context;

    /**
     *
     */
    private MockMvc mvc;

    @Before
    public void before() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testOriginList() throws Exception {
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get("/test/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        logger.info("user: {}", mvcResult.getResponse().getContentAsString());
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: get(Long id)
     */
    @Test
    public void testGet() throws Exception {
//TODO: Test goes here... 
    }


}
