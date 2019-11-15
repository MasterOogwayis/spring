package com.zsw.persistence.repository;

import com.anze.base.persistence.repository.BaseRepository;
import com.zsw.persistence.entity.IcbcOrder;
import com.zsw.persistence.repository.custom.IcbcOrderRepositoryCustom;
import org.springframework.stereotype.Repository;

@Repository
public interface IcbcOrderRepository extends BaseRepository<IcbcOrder, Long>, IcbcOrderRepositoryCustom {


}
