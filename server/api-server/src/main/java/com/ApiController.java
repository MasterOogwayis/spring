package com;

import com.zsw.conf.base.saleorder.SaleOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2018/1/12 14:48
 */
@RestController
@RequestMapping("api")
public class ApiController {

    /**
     *
     */
    @Autowired
    private ApiService apiService;

    /**
     * @param saleOrderDto
     * @return
     */
    @PostMapping("edit")
    public SaleOrderDto edit(@ModelAttribute final SaleOrderDto saleOrderDto) {
        SaleOrderDto data = this.apiService.edit(saleOrderDto);
        return data;
    }


}
