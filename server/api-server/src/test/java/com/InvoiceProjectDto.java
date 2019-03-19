package com;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

/**
 * 项目信息（发票明细）
 *
 * @author ZhangShaowei on 2018/11/20 17:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceProjectDto implements Serializable {

    /**
     * 项目名称
     * 如FPHXZ=1，则此商品行为折扣行，此版本折扣行不允许多行折扣，
     * 折扣行必须紧邻被折扣行，项目名称必须与被折扣行一致。
     *
     * length = 90
     * required = true
     */
    @NonNull
    private String XMMC;

    /**
     * 项目单位
     * 成品油发票项目单位为1 位并且单位为（吨）或（升）
     *
     * length = 22
     * required = false
     */
    private String XMDW;

    /**
     * 规格型号
     *
     * length = 40
     * required = false
     */
    private String GGXH;

    /**
     * 项目数量
     * 小数点后8 位，小数点后都是0 时，PDF 上只显示整数。
     * 开具红票时，项目数量为负数。
     * 成品油发票必填
     *
     * length = 24
     * required = false
     */
    private String XMSL;

    /**
     * 含税标志
     * 表示项目单价和项目金额是否含税。0 表示都不含税，1 表示都含税。
     *
     * length = 1
     * required = true
     */
    @NonNull
    private String HSBZ;

    /**
     * 发票行性质 0 正常行、1 折扣行、2 被折扣行
     *
     * length = 1
     * required = true
     */
    @NonNull
    private String FPHXZ;

    /**
     * 项目单价
     * 小数点后8 位，小数点后都是0 时，
     * PDF 上只显示2 位小数；否则只显示至最后一位不为0 的数字
     * 成品油发票必填
     *
     * length = 24
     * required = true
     */
    @NonNull
    private String XMDJ;

    /**
     * 商品编码
     * 税收分类编码
     * 1、成品油发票只能开具成品油商品编码
     * 2、非成品油发票不能开具成品油编码
     *
     * length = 19
     * required = true
     */
    @NonNull
    private String SPBM;

    /**
     * 自行编号
     *
     * length = 16
     * required = false
     */
    private String ZXBM;

    /**
     * 优惠政策标识 0：不使用，1：使用
     *
     * length = 1
     * required = true
     */
    @NonNull
    private String YHZCBS;

    /**
     * 零税率标识 null：非零税率，0：出口零税率，1：免税，2：不征税，3：普通零税率
     *
     * length = 1
     * required = false
     */
    private String LSLBS;

    /**
     * 增值税特殊管理 当YHZCBS 为1 时必填
     *
     * length = 50
     * required = false
     */
    private String ZZSTSGL;

    /**
     * 扣税额
     * 单位元，小数点2 位小数不能大于不含税金额
     * 说明如下：
     * 1.差额征税的发票如果没有折扣的话，只能允许一条商品行。
     * 2.具体差额征税发票的计算方法如下：不含税差额= 不含税金额- 扣除额；税额= 不含税差额*税率。
     *
     * length = 24
     * required = false
     */
    private String KCE;

    /**
     * TODO
     * 项目金额
     * 小数点后2 位，以元为单位精确到分。等于=单价*数量，根据含税标志，确定此金额是否为含税金额。
     *
     * length = 16
     * required = true
     */
    @NonNull
    private String XMJE;

    /**
     * 税率 如果税率为0，表示免税
     *
     * length = 10
     * required = true
     */
    @NonNull
    private String SL;

    /**
     * 税额 小数点后2 位，以元为单位精确到分
     *
     * length = 20
     * required = false
     */
    private String SE;

    /**
     * 备用字段
     */
    private String BYZD1;

    /**
     * 备用字段
     */
    private String BYZD2;

    /**
     * 备用字段
     */
    private String BYZD3;

    /**
     * 备用字段
     */
    private String BYZD4;

    /**
     * 备用字段
     */
    private String BYZD5;

}
