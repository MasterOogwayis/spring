package com.zsw;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ZhangShaowei on 2021/8/25 10:06
 */
@Rollback
@SpringBootTest(classes = SpringBootFeaturesTests.class)
@AutoConfigureMockMvc
public class ApplicationTests {


    @Test
    public void exampleTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("Hello World"));
    }

    @Test
    public void test(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/config"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.profiles").value("dev"))
                .andExpect(jsonPath("$.db").value("redis"));
//                .andExpect(content().string("Hello World"));
    }

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


}
