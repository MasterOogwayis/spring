package com.zsw.orm.peprsistence.repository;

import com.zsw.orm.peprsistence.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Ticket Repository
 *
 * @author ZhangShaowei on 2018/3/23 13:23
 **/
//@Repository
//@Qualifier("ticketRepository")
@NoRepositoryBean
public interface TicketRepository extends JpaRepository<Ticket, String>{
}
