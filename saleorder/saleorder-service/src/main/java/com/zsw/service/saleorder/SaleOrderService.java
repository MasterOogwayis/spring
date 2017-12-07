package com.zsw.service.saleorder;

import com.zsw.base.redis.dao.commons.BaseCacheDao;
import com.zsw.base.service.BaseService;
import com.zsw.persistence.bean.Product;
import com.zsw.persistence.bean.SaleOrder;
import com.zsw.persistence.repository.SaleOrderRepository;
import com.zsw.service.product.ProductService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2017/10/12 15:15
 */
@Service("saleOrderService")
@Transactional(rollbackFor = Exception.class)
public class SaleOrderService extends BaseService {

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

    /**
     * @param id id
     * @return
     */
    public String test(final Long id) {
        return "Cachelock";
    }

    /**
     * @param id product id
     */
    public void concurrent(final Long id) {
        for (int i = 0; i < 1000; i++) {
            threadPoolExecutor.execute(() -> {
                Integer num = 1 + RandomUtils.nextInt(2);
                this.order(id, num.longValue());
            });
        }


    }

    /**
     * @param id id
     * @return SaleOrder
     */
    @Cacheable(value = "list", key = "'saleorder:' + #id")
    public SaleOrder getSaleOrder(final Long id) {
        return this.saleOrderRepository.findOne(id);
    }


    /**
     * @param id  product id
     * @param num num
     * @return
     */
    public String order(final Long id, final Long num) {
        String key = "lock";
        String ticket = "ticket_" + id;
        try {
            if (!this.lock(key, 2 * 1000, 10 * 1000)) {
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
            this.saleOrderRepository.save(saleOrder);
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
