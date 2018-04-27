package com.api;

import com.persistence.entity.Ticket;
import com.persistence.respoistory.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ticket
 *
 * @author ZhangShaowei on 2018/3/23 13:24
 **/
@RestController
@RequestMapping("ticket")
public class TicketController {

    /**
     *
     */
    @Autowired
    private TicketRepository ticketRepository;

    /**
     * @param ticket
     * @return
     */
    @PostMapping("add")
    public Ticket add(@ModelAttribute Ticket ticket) {
        return this.ticketRepository.save(ticket);
    }

    /**
     * @param page
     * @param pageSize
     * @return
     */
    @PostMapping("find")
    public Page<Ticket> find(Integer page, Integer pageSize) {
        return this.ticketRepository.findAll(new PageRequest(page - 1, pageSize));
    }

    /**
     * @param ticket
     * @return
     */
    @PostMapping("findBy")
    public List<Ticket> findBy(@ModelAttribute Ticket ticket) {
        return this.ticketRepository.findAll(Example.of(ticket));
    }


}
