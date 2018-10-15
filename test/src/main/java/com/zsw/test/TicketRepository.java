package com.zsw.test;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZhangShaowei on 2018/10/11 17:49
 **/
@Repository
@Qualifier("ticketRepository")
public interface TicketRepository extends JpaRepository<Ticket, String> {
}
