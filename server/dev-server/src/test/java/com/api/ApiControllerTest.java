package com.api;

import com.DevApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ApiController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 23, 2018</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevApplication.class)
public class ApiControllerTest {

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

    @After
    public void after() throws Exception {
    }

    /**
     * Method: log(String msg)
     */
    @Test
    public void testLog() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/dev/log")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("msg", "消息")
                        .param("age", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").value("消息"));
//TODO: Test goes here...
    }


} 