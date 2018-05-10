package com.zsw.base.peprsistence.repository;

import com.zsw.base.peprsistence.entity.Ticket;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

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
