package com.zsw.service.saleorder.impl;

import com.zsw.base.redis.dao.commons.BaseCacheDao;
import com.zsw.base.service.impl.BaseServiceImpl;
import com.zsw.conf.base.saleorder.SaleOrderDto;
import com.zsw.persistence.bean.Product;
import com.zsw.persistence.bean.SaleOrder;
import com.zsw.persistence.repository.SaleOrderRepository;
import com.zsw.service.product.ProductService;
import com.zsw.service.saleorder.SaleOrderService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2017/10/12 15:15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SaleOrderServiceImpl extends BaseServiceImpl<SaleOrder, Long> implements SaleOrderService {

    /**
     *
     */
    private static final int TIMEOUT = 2 * 1000;

    /**
     *
     */
    private static final int EXPIRE = 20 * 1000;

    /**
     *
     */
    @Autowired
    private ProductService productService;

    /**
     *
     */
    @Autowired
    private SaleOrderRepository saleOrderRepository;


    /**
     *
     */
    @Autowired
    private BaseCacheDao cache;

    /**
     *
     */
    @Autowired
    private Executor threadPoolExecutor;

    @Override
    public JpaRepository<SaleOrder, Long> getRepository() {
        return this.saleOrderRepository;
    }

    /**
     * @param id id
     * @return SaleOrder
     */
    @Override
    @Cacheable(value = "list", key = "'saleorder:' + #id")
    public SaleOrder getCached(final Long id) {
        return super.get(id);
    }


    /**
     * @param saleOrderDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "list", key = "'saleorder:' + #saleOrderDto.getId()")
    public SaleOrder edit(final SaleOrderDto saleOrderDto) {

        SaleOrder saleOrder = this.get(saleOrderDto.getId());

//        msgService.transactionSimpleMessage(new BaseMqMessage());

        this.copyProperties(saleOrderDto, saleOrder, "id", "product");

        this.save(saleOrder);

//        if (1 == 1) {
//            throw new RuntimeException("This is a Test");
//        }

        return saleOrder;
    }


    /**
     * @param id  product id
     * @param num num
     * @return
     */
    @Override
    public String order(final Long id, final Long num) {
        String key = "lock";
        String ticket = "ticket_" + id;
        try {
            if (!this.lock(key, TIMEOUT, EXPIRE)) {
                return "Timeout, please try latter";
            }
            long left = this.leftTicket(ticket, id);
            if (left >= num) {
                this.cache.decrement(ticket, num);
                this.save(id, num.intValue());
                return "success";
            } else {
                return "余票不足";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {
            this.unlock(key);
        }


    }

    /**
     * @param id  product id
     * @param num num
     */
    private void save(final Long id, final int num) {
        threadPoolExecutor.execute(() -> {
            SaleOrder saleOrder = new SaleOrder();
            saleOrder.setQuantity(num);
            saleOrder.setCreateTimestamp(new Date());
            Product product = this.productService.get(id);
            saleOrder.setProduct(product);
            this.save(saleOrder);
        });
    }

    /**
     * @param ticketKey ticketKey
     * @param id        product id
     * @return left ticket
     */
    private long leftTicket(final String ticketKey, final Long id) {
        Object leftTicket = this.cache.get(ticketKey);
        if (Objects.isNull(leftTicket)) {
            Product product = this.productService.get(id);
            this.cache.set(ticketKey, product.getNumber());
            return product.getNumber();
        }
        return Long.parseLong(leftTicket.toString());
    }


    /**
     * @param key     key
     * @param timeout timeout
     * @param expire  expire
     * @return setNx?true:false
     */
    private boolean lock(final String key, final long timeout, final long expire) {
        long millTime = System.currentTimeMillis();
        boolean locked = false;
        try {
            //在timeout的时间范围内不断轮询锁
            while (System.currentTimeMillis() - millTime < timeout) {
                //锁不存在的话，设置锁并设置锁过期时间，即加锁
                if (this.cache.setNXExpire(key, "locked", expire, TimeUnit.MILLISECONDS)) {
                    //锁的情况下锁过期后消失，不会造成永久阻塞
                    locked = true;
                    break;
                }
                //短暂休眠，避免可能的活锁
                Thread.sleep(5, RandomUtils.nextInt(10));
            }
        } catch (Exception e) {
            throw new RuntimeException("locking error", e);
        }
        return locked;
    }

    /**
     * @param key
     */
    private void unlock(final String key) {
        this.cache.del(key);
    }


}
