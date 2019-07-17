package com.demo; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 
import org.junit.runner.RunWith;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/** 
* ProductService Tester. 
* 
* @author <Authors name> 
* @since <pre>07/17/2019</pre> 
* @version 1.0 
*/ 
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DevApplication.class)
@RunWith(Parameterized.class)
public class ProductServiceTest { 

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[2][0]; // repeat count which you want
    }


/**
//     *
//     */
//    @Autowired
//    private WebApplicationContext context;
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
     *
     * Method: get(Long id)
     * {@link ProductService#get} 
     * 
     */ 
    @Test
    public void testGet() throws Exception { 
    //TODO: Test goes here... 
    } 


} 
