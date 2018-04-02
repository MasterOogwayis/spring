package com.persistence.respoistory;

import com.persistence.entity.Ticket;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Ticket Repository
 *
 * @author ZhangShaowei on 2018/3/23 13:23
 **/
@Repository
@Qualifier("ticketRepository")
public interface TicketRepository extends JpaRepository<Ticket, String>{
}
