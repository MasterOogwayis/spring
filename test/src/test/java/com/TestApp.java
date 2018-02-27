package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsw.conf.base.saleorder.ProductDto;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.bean.SaleOrder;
import com.zsw.service.saleorder.SaleOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This is a test file.
 *
 * @author ZhangShaowei on 2018/2/22 16:17
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestApp {

    /**
     *
     */
    private static final String ADMIN = "admin";

    private final Gson gson = new GsonBuilder().create();


    @Autowired
    private SaleOrderService saleOrderService;

    @Test
//    @WithMockUser 虚拟用户
    @WithUserDetails(ADMIN)
    public void test() {

        System.err.println(this.getMessage());

        SaleOrder saleOrder = this.saleOrderService.getSaleOrder(1L);
        SaleOrderDto saleOrderDto = new SaleOrderDto();
        BeanUtils.copyProperties(saleOrder, saleOrderDto, "PRODUCT");
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(saleOrder.getProduct(), productDto);
        saleOrderDto.setProduct(productDto);
        System.out.println(this.gson.toJson(saleOrderDto));
    }


    /**
     * @return
     */
    @PreAuthorize("authenticated")
    public String getMessage() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return "Hello " + this.gson.toJson(authentication);
    }

}
