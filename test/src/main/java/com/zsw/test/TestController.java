package com.zsw.test;

import com.zsw.base.ui.commons.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2018/10/11 17:14
 **/
@RestController
@RequestMapping("test")
public class TestController extends BaseController {


    @Autowired
    private TicketService ticketService;


    @PostMapping("ticket")
    public Ticket save(@RequestBody String requestBody) {
        Ticket ticket = this.gson.fromJson(requestBody, Ticket.class);
        return this.ticketService.save(ticket);
    }


}
