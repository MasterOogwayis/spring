package com.zsw.business;

import com.github.pagehelper.PageHelper;
import com.zsw.persistence.entity.SaleOrder;
import com.zsw.service.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZhangShaowei on 2020/5/12 11:24
 */
@RequestMapping("test")
@RestController
public class AllController {

    @Autowired
    private SaleOrderService saleOrderService;

    @GetMapping("get")
    public SaleOrder apple(@RequestParam("id") Long id) {
        return this.saleOrderService.getById(id);
    }

    @GetMapping("findAll")
    public List<SaleOrder> apple(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return this.saleOrderService.findAll();
    }

    @GetMapping("find")
    public List<SaleOrder> find() {
        return this.saleOrderService.find();
    }

}
