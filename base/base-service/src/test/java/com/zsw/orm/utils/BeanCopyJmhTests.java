package com.zsw.orm.utils;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2023-7-20
 */
@Slf4j
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(2)
@Warmup(iterations = 2, time = 5)
@Measurement(iterations = 10, time = 5)
public class BeanCopyJmhTests {

    private A source;

    private B target;

    @SneakyThrows
    @Setup(Level.Trial)
    public void init() {
        this.source = getData();
        this.target = new B();
    }

    private static A getData() {
        A a = new A();
        a.setId(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));
        a.setGid(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));
        a.setBid(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));
        a.setSid(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));
        a.setPlatform(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE));
        a.setThirdOrderId(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE) + "");
        a.setOrderno(UUID.randomUUID().toString());
        a.setRemark(UUID.randomUUID().toString());
        a.setShopName(UUID.randomUUID().toString());
        a.setRecipientAddress(UUID.randomUUID().toString());
        a.setInvoice(UUID.randomUUID().toString());
        a.setInvoiceEmail(UUID.randomUUID().toString());
        a.setOrderType(1);
        a.setRecipientPhone("12312312312");
        a.setRecipientRealPhone("11111111111");
        a.setBackupRecipientPhone("22222222222");
        a.setRecipientPhoneEnd("0980");
        a.setRecipientName("unknown");
        a.setStatus(1);
        a.setRefundStatus(2);
        a.setRefundType(1);
        a.setDispatchType(2);
        a.setDeliveryTime(System.currentTimeMillis());
        a.setDeliveryNotifyTime(System.currentTimeMillis());
        a.setEstimateArrivalTime(System.currentTimeMillis());
        a.setIsThirdShipping(1);
        a.setPickType(2);
        a.setPickCode("3");
        a.setDaySeq("4");
        a.setDinnersNumber(5);
        a.setLogisticsCode("6");
        a.setDeliveryFee(7);
        a.setMerchantEliverySubsidy(8);
        a.setVipDeliveryFeeDiscount(9);
        a.setTotalPrice(10);
        a.setOriginalPrice(11);
        a.setPayType(12);
        a.setLatitude("13");
        a.setLongitude("14");
        a.setInvoiced(15);
        a.setIncome(16);
        a.setServiceRate(17);
        a.setServiceFee(18);
        a.setPlatformServiceFee(19);
        a.setPackageFee(20);
        a.setActivityTotal(21);
        a.setInvoiceType(22);
        a.setTaxpayerId("23");
        a.setCancelOrderDescription("24");
        a.setFulfillServiceFee(25);
        a.setTotalAmount(26);
        a.setTotalActivityAmount(27);
        a.setDeliveryPackageFee(28);
        a.setUserIdStr("29");
        a.setOrderSendTime(30);
        a.setTakeTime(31);
        a.setRemindFlag(32);
        a.setApplyRefundFlag(33);
        a.setMealCompleteFlag(34);
        a.setShopRemark("35");
        a.setPublicWelfareGoodsFee(36);
        a.setCreateTime(37);
        a.setUpdateTime(38);
        a.setDeleted(39);
        return a;
    }


    @Benchmark
    public void test() {
        FastCopy.copyProperties(this.source, this.target);
    }

    @Benchmark
    public void testCglib() {
        CglibBeanUtils.copy(this.source, this.target);
    }


    @Data
    public static class A {
        /**
         * 主键
         */
        private Long id;

        /**
         * 集团ID
         */
        private Long gid;

        /**
         * 品牌ID
         */
        private Long bid;

        /**
         * 门店ID
         */
        private Long sid;

        /**
         * 平台 1=美团 2=饿了么
         */
        private Integer platform;

        /**
         * 第三方订单ID
         */
        private String thirdOrderId;

        /**
         * 订单展示的ID
         */
        private String orderno;

        /**
         * 忌口或备注
         */
        private String remark;

        /**
         * 门店名称
         */
        private String shopName;

        /**
         * 顾客送餐地址
         */
        private String recipientAddress;

        /**
         * 发票抬头
         */
        private String invoice;

        /**
         * 用户收取发票的邮箱
         */
        private String invoiceEmail;

        /**
         * 订单类型 1=即时单 2=预订单
         */
        private Integer orderType;

        /**
         * 收件人电话
         */
        private String recipientPhone;

        /**
         * 收件人真实电话
         */
        private String recipientRealPhone;

        /**
         * 备用隐私号["xxxx","xxxx"]
         */
        private String backupRecipientPhone;

        /**
         * 用户真实手机号后4位
         */
        private String recipientPhoneEnd;

        /**
         * 收件人姓名
         */
        private String recipientName;

        /**
         * 订单状态 1=待付款 2=待接单 3=待制作 4=待配送 5=配送中 6=待取餐 7=待评价 8=已完成 9=已取消
         */
        private Integer status;

        /**
         * 退菜状态 1=未退菜 2=部分退菜 3=全部退菜
         */
        private Integer refundStatus;

        /**
         * 退款类型 1= 未退款 2=部分退款(退菜) 3=全部退款(退款)
         */
        private Integer refundType;

        /**
         * 配送状态，2=派单中 3=骑手接单 4=取餐中 5=骑手到店 6=配送中 7=配送已完成 8=配送取消 9=配送异常
         */
        private Integer dispatchType;

        /**
         * 预订单用户选择的预计送达时间
         */
        private Long deliveryTime;

        /**
         * 预订单用户通知后厨时间
         */
        private Long deliveryNotifyTime;

        /**
         * 即时单的预计送达时间
         */
        private Long estimateArrivalTime;

        /**
         * 是否是第三方配送平台配送1是 2否
         */
        private Integer isThirdShipping;

        /**
         * 取餐类型 1=外卖 2=到店自取
         */
        private Integer pickType;

        /**
         * 取餐号（自提）
         */
        private String pickCode;

        /**
         * 门店当天的推单流水号
         */
        private String daySeq;

        /**
         * 用餐人数
         */
        private Integer dinnersNumber;

        /**
         * 订单配送方式
         */
        private String logisticsCode;

        /**
         * 配送费
         */
        private Integer deliveryFee;

        /**
         * 商家替用户承担的配送费
         */
        private Integer merchantEliverySubsidy;

        /**
         * 会员减配送费
         */
        private Integer vipDeliveryFeeDiscount;

        /**
         * 用户实际支付的金额
         */
        private Integer totalPrice;

        /**
         * 商品原价
         */
        private Integer originalPrice;

        /**
         * 支付类型，1表货到付款，2表在线支付
         */
        private Integer payType;

        /**
         * 实际送餐地址纬度
         */
        private String latitude;

        /**
         * 实际送餐地址经度
         */
        private String longitude;

        /**
         * 顾客是否需要发票1是 2否
         */
        private Integer invoiced;

        /**
         * 店铺收入
         */
        private Integer income;

        /**
         * 服务费率
         */
        private Integer serviceRate;

        /**
         * 抽佣服务费(技术服务费)
         */
        private Integer serviceFee;

        /**
         * 平台服务费
         */
        private Integer platformServiceFee;

        /**
         * 餐盒费
         */
        private Integer packageFee;

        /**
         * 订单活动总额
         */
        private Integer activityTotal;

        /**
         * 发票类型1:个人 2:企业
         */
        private Integer invoiceType;

        /**
         * 纳税人识别号
         */
        private String taxpayerId;

        /**
         * 用户取消原因
         */
        private String cancelOrderDescription;

        /**
         * 履约服务费
         */
        private Integer fulfillServiceFee;

        /**
         * 总合计金额 = 菜品总原 + 餐盒费总原 + 配送费总原价
         */
        private Integer totalAmount;

        /**
         * 总优惠金额 = 总合计金额 - 顾客实付
         */
        private Integer totalActivityAmount;

        /**
         * 配送费原价 + 餐盒费
         */
        private Integer deliveryPackageFee;

        /**
         * 下单用户的id
         */
        private String userIdStr;

        /**
         * 用户下单时间
         */
        private Integer orderSendTime;

        /**
         * 取餐时间
         */
        private Integer takeTime;

        /**
         * 催单标识
         */
        private Integer remindFlag;

        /**
         * 申请退款标识
         */
        private Integer applyRefundFlag;

        /**
         * 是否已出餐(1:是,2:否)
         */
        private Integer mealCompleteFlag;

        /**
         * 商家备注
         */
        private String shopRemark;

        /**
         * 爱心商家捐赠金额
         */
        private Integer publicWelfareGoodsFee;

        /**
         * 创建时间
         */
        private Integer createTime;

        /**
         * 修改时间
         */
        private Integer updateTime;

        /**
         * 是否删除(1:删除；2:不删除)
         */
        private Integer deleted;
    }

    @Data
    public static class B {
        /**
         * 主键
         */
        private Long id;

        /**
         * 集团ID
         */
        private Long gid;

        /**
         * 品牌ID
         */
        private Long bid;

        /**
         * 门店ID
         */
        private Long sid;

        /**
         * 平台 1=美团 2=饿了么
         */
        private Integer platform;

        /**
         * 第三方订单ID
         */
        private String thirdOrderId;

        /**
         * 订单展示的ID
         */
        private String orderno;

        /**
         * 忌口或备注
         */
        private String remark;

        /**
         * 门店名称
         */
        private String shopName;

        /**
         * 顾客送餐地址
         */
        private String recipientAddress;

        /**
         * 发票抬头
         */
        private String invoice;

        /**
         * 用户收取发票的邮箱
         */
        private String invoiceEmail;

        /**
         * 订单类型 1=即时单 2=预订单
         */
        private Integer orderType;

        /**
         * 收件人电话
         */
        private String recipientPhone;

        /**
         * 收件人真实电话
         */
        private String recipientRealPhone;

        /**
         * 备用隐私号["xxxx","xxxx"]
         */
        private String backupRecipientPhone;

        /**
         * 用户真实手机号后4位
         */
        private String recipientPhoneEnd;

        /**
         * 收件人姓名
         */
        private String recipientName;

        /**
         * 订单状态 1=待付款 2=待接单 3=待制作 4=待配送 5=配送中 6=待取餐 7=待评价 8=已完成 9=已取消
         */
        private Integer status;

        /**
         * 退菜状态 1=未退菜 2=部分退菜 3=全部退菜
         */
        private Integer refundStatus;

        /**
         * 退款类型 1= 未退款 2=部分退款(退菜) 3=全部退款(退款)
         */
        private Integer refundType;

        /**
         * 配送状态，2=派单中 3=骑手接单 4=取餐中 5=骑手到店 6=配送中 7=配送已完成 8=配送取消 9=配送异常
         */
        private Integer dispatchType;

        /**
         * 预订单用户选择的预计送达时间
         */
        private Long deliveryTime;

        /**
         * 预订单用户通知后厨时间
         */
        private Long deliveryNotifyTime;

        /**
         * 即时单的预计送达时间
         */
        private Long estimateArrivalTime;

        /**
         * 是否是第三方配送平台配送1是 2否
         */
        private Integer isThirdShipping;

        /**
         * 取餐类型 1=外卖 2=到店自取
         */
        private Integer pickType;

        /**
         * 取餐号（自提）
         */
        private String pickCode;

        /**
         * 门店当天的推单流水号
         */
        private String daySeq;

        /**
         * 用餐人数
         */
        private Integer dinnersNumber;

        /**
         * 订单配送方式
         */
        private String logisticsCode;

        /**
         * 配送费
         */
        private Integer deliveryFee;

        /**
         * 商家替用户承担的配送费
         */
        private Integer merchantEliverySubsidy;

        /**
         * 会员减配送费
         */
        private Integer vipDeliveryFeeDiscount;

        /**
         * 用户实际支付的金额
         */
        private Integer totalPrice;

        /**
         * 商品原价
         */
        private Integer originalPrice;

        /**
         * 支付类型，1表货到付款，2表在线支付
         */
        private Integer payType;

        /**
         * 实际送餐地址纬度
         */
        private String latitude;

        /**
         * 实际送餐地址经度
         */
        private String longitude;

        /**
         * 顾客是否需要发票1是 2否
         */
        private Integer invoiced;

        /**
         * 店铺收入
         */
        private Integer income;

        /**
         * 服务费率
         */
        private Integer serviceRate;

        /**
         * 抽佣服务费(技术服务费)
         */
        private Integer serviceFee;

        /**
         * 平台服务费
         */
        private Integer platformServiceFee;

        /**
         * 餐盒费
         */
        private Integer packageFee;

        /**
         * 订单活动总额
         */
        private Integer activityTotal;

        /**
         * 发票类型1:个人 2:企业
         */
        private Integer invoiceType;

        /**
         * 纳税人识别号
         */
        private String taxpayerId;

        /**
         * 用户取消原因
         */
        private String cancelOrderDescription;

        /**
         * 履约服务费
         */
        private Integer fulfillServiceFee;

        /**
         * 总合计金额 = 菜品总原 + 餐盒费总原 + 配送费总原价
         */
        private Integer totalAmount;

        /**
         * 总优惠金额 = 总合计金额 - 顾客实付
         */
        private Integer totalActivityAmount;

        /**
         * 配送费原价 + 餐盒费
         */
        private Integer deliveryPackageFee;

        /**
         * 下单用户的id
         */
        private String userIdStr;

        /**
         * 用户下单时间
         */
        private Integer orderSendTime;

        /**
         * 取餐时间
         */
        private Integer takeTime;

        /**
         * 催单标识
         */
        private Integer remindFlag;

        /**
         * 申请退款标识
         */
        private Integer applyRefundFlag;

        /**
         * 是否已出餐(1:是,2:否)
         */
        private Integer mealCompleteFlag;

        /**
         * 商家备注
         */
        private String shopRemark;

        /**
         * 爱心商家捐赠金额
         */
        private Integer publicWelfareGoodsFee;

        /**
         * 创建时间
         */
        private Integer createTime;

        /**
         * 修改时间
         */
        private Integer updateTime;

        /**
         * 是否删除(1:删除；2:不删除)
         */
        private Integer deleted;
    }


}
