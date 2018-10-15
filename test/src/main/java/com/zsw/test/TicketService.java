package com.zsw.test;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangShaowei on 2018/10/11 17:25
 **/
@Setter
@Service
@Transactional(rollbackFor = Exception.class)
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;


    public Ticket save(Ticket ticket) {
        return this.ticketRepository.save(ticket);
    }

}
