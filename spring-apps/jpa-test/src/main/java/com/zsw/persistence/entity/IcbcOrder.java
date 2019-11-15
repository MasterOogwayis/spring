package com.zsw.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * icbc 支付下单签名
 * <p>
 * 由于计算规则没有变化，这里仅记录请求过来的参数
 * 实际返回只带证书和公钥，不保存数据库
 *
 * @author Shaowei Zhang
 * @since 2019-07-01 17:39:47
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "fc_icbc_order",
        indexes = @Index(name = "idx_orderNumber", columnList = "orderNumber", unique = false)
)
@org.hibernate.annotations.Table(appliesTo = "fc_icbc_order", comment = "订单签名")
public class IcbcOrder implements Serializable {

    private static final long serialVersionUID = -3195541657191629129L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 19)
    protected Long id;

    /**
     * 订单号
     */
    @Column(name = "orderNumber", nullable = false, columnDefinition = "VARCHAR(30) NOT NULL COMMENT '订单号'")
    private String orderNumber;


    /**
     * 金额，单位：分
     */
    @Column(name = "amount", nullable = false, columnDefinition = "bigint(20) NOT NULL COMMENT '金额，单位：分'")
    private Long amount;


    /**
     * 下单日期
     */
    @Column(name = "orderDate", nullable = false, columnDefinition = "datetime NOT NULL COMMENT '下单日期'")
    private Date orderDate;

    /**
     * 交易过期时间
     */
    @Column(name = "expireTime", nullable = false, columnDefinition = "datetime NOT NULL COMMENT '交易过期时间'")
    private Date expireTime;


    /**
     * 商品 id 选填
     */
    @Column(name = "goodsId", columnDefinition = "varchar(50) COMMENT '商品 id'")
    private String goodsId;

    /**
     * 商品名称 选填
     */
    @Column(name = "goodsName", columnDefinition = "varchar(50) COMMENT '商品名称'")
    private String goodsName;

    /**
     * 商品数量 选填
     */
    @Column(name = "goodsNum", columnDefinition = "int(2) NOT NULL COMMENT '商品数量'")
    private Integer goodsNum;


    /**
     * 创建
     */
    @Column(name = "createDate", nullable = false, columnDefinition = "datetime NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createDate;

    /**
     * 附加信息
     */
    @Column(name = "message", columnDefinition = "varchar(200)")
    private String message;

    @Column(name = "clientType", nullable = false, columnDefinition = "varchar(20) NOT NULL COMMENT 'AliApp:支付宝，WeChatApp:微信，iPhoneClient:工行ios端，AndroidClient:工行android端'")
    private String clientType;


}

