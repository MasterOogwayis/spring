package com;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * 发票信息
 *
 * @author ZhangShaowei on 2018/11/20 17:27
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ZService("InvoiceInfoDto")
public class InvoiceInfoDto implements Serializable {

    /**
     * TODO
     * 发票请求唯一流水
     *
     * length = 20
     * required = true
     */
    @NonNull
    private String FPQQLSH;

    /**
     * TODO
     * 电商平台编码
     * 企业电商平台编码
     *
     * length = 8
     * required = true
     */
    @NonNull
    private String DSPTBM;

    /**
     * 纳税人识别号
     * length = 20
     * required = true
     */
    @NonNull
    private String NSRSBH;

    /**
     * TODO
     * 纳税人名称
     *
     * length = 100
     * required = true
     */
    @NonNull
    private String NSRMC;

    /**
     * 纳税人电子档案号
     *
     * length = 20
     * required = false
     */
    private String NSRDZDAH;

    /**
     * 税务机构代码
     *
     * length = 11
     * required = false
     */
    private String SWJG_DM;

    /**
     * 代开标志 必须为 0
     * length = 1
     * required = true
     */
    @NonNull
    private String DKBZ = "0";

    /**
     * 收购标志
     * length = 1
     * required = false
     */
    private String SGBZ;

    /**
     * 票样代码 全部固定为  000001
     * length = 6
     * required = true
     */
    @NonNull
    private String PYDM = "000001";

    /**
     * 主要开票项目
     * 主要开票商品，或者第一条商品，取项目信息中第一条
     * 数据的项目名称（或传递大类例如：办公用品）
     *
     * length = 90
     * required = true
     */
    @NonNull
    private String KPXM;

    /**
     * 编码表版本号
     *
     * length = 20
     * required = true
     */
    @NonNull
    private String BMB_BBH;

    /**
     * TODO
     * 销货方识别号
     * 必填，如果是企业自营开具发票，填写第3 项中的开票方识别号，
     * 如果是商家驻店开具发票，填写商家的纳税人识别号
     *
     * length = 20
     * required = true
     */
    @NonNull
    private String XHF_NSRSBH;

    /**
     * TODO
     * 销货方名称
     * 必填，纳税人名称
     *
     * length = 100
     * required = true
     */
    @NonNull
    private String XHFMC;

    /**
     * 销货方地址
     *
     * length = 80
     * required = true
     */
    @NonNull
    private String XHF_DZ;

    /**
     * 销货方电话
     *
     * length = 20
     * required = true
     */
    @NonNull
    private String XHF_DH;

    /**
     * 销货方银行账号
     * 开户行及账号
     *
     * length = 100
     * required = false
     */
    private String XHF_YHZH;

    /**
     * 购货方名称
     * 购货方名称，即发票抬头
     *
     * length = 100
     * required = true
     */
    @NonNull
    private String GHFMC;

    /**
     * 购货方识别号
     * 企业消费，如果填写识别号，需要传输过来
     *
     * length = 20
     * required = false
     */
    private String GHF_NSRSBH;

    /**
     * 购货方地址
     *
     * length = 80
     * required = false
     */
    private String GHF_DZ;

    /**
     * 购货方省份
     * 新增，使用各省的行政编码，例如：上海21
     *
     * length = 20
     * required = false
     */
    private String GHF_SF;

    /**
     * 购货方固定电话
     *
     * length = 20
     * required = false
     */
    private String GHF_GDDH;

    /**
     * 购货方手机
     *
     * length = 20
     * required = true
     */
    @NonNull
    private String GHF_SJ;

    /**
     * 购货方邮箱
     *
     * length = 50
     * required = false
     */
    private String GHF_EMAIL;

    /**
     * 购货方企业类型    01：企业  02：机关事业单位  03：个人  04：其它
     *
     * length = 2
     * required = true
     */
    @NonNull
    private String GHFQYLX;

    /**
     * 购货方银行账号
     * 开户行及账号
     *
     * length = 100
     * required = false
     */
    private String GHF_YHZH;

    /**
     * 行业代码
     * 由企业端系统自动填写（根据企业注册信息）
     * length = 10
     * required = false
     */
    private String HY_DM;

    /**
     * 行业名称
     * 由企业端系统自动填写（根据企业注册信息）
     * length = 40
     * required = false
     */
    private String HY_MC;

    /**
     * TODO
     * 开票员-开票人
     *
     * length = 8
     * required = true
     */
    @NonNull
    private String KPY;

    /**
     * 收款员
     *
     * length = 8
     * required = false
     */
    private String SKY;

    /**
     * 复核人
     *
     * length = 8
     * required = false
     */
    private String FHR;

    /**
     * 开票日期 格式 yyyy-MM-dd HH:mm:ss(由开票系统生成)
     *
     * length = 19
     * required = false
     */
    private String KPRQ;

    /**
     * 开票类型   1 正票   2 红票
     *
     * length = 1
     * required = true
     */
    @NonNull
    private String KPLX;

    /**
     * 发票种类代码
     * 增值税普通电子发票：51
     * 增值税普通发票（纸票）： 2
     * 增值税专用发票：0
     *
     * length = 5
     * required = true
     */
    @NonNull
    private String FPZL_DM;

    /**
     * TODO
     * 原发票代码，如果CZDM 不是10 或 KPLX 为红票时候都是必录
     *
     * length = 12
     * required = false
     */
    private String YFP_DM;

    /**
     * TODO
     * 原发票号码，如果CZDM 不是10 或 KPLX 为红票时候都是必录
     *
     * length = 8
     * required = false
     */
    private String YFP_HM;

    /**
     * TODO
     * 特殊冲红标志 0 正常冲红(电子发票) 1 特殊冲红(冲红纸质等)
     *
     * length = 1
     * required = true
     */
    @NonNull
    private String TSCHBZ = "0";

    /**
     * TODO
     * 操作代码
     * 10 正票正常开具
     * 11 正票错票重开
     * 20 退货折让红票、
     * 21 错票重开红票、
     * 22 换票冲红（全冲红电子发票，开具纸质发票）
     *
     * length = 2
     * required = true
     */
    @NonNull
    private String CZDM;

    /**
     * TODO
     * 清单标志
     * 0-普通电子发票；
     * 1-普通电子发票（清单）；
     * 2-收购电子发票；
     * 2-收购电子发票（清单）；
     * 4-成品油电子发票
     * 纸票默认为0
     *
     * length = 1
     * required = true
     */
    @NonNull
    private String QD_BZ = "0";

    /**
     * TODO
     * 清单发票项目名称
     * 需要打印清单时对应发票票面项目名称
     * 清单标识（QD_BZ）为1 时必填。为0 不进行处理。
     *
     * length = 90
     * required = false
     */
    private String QDXMMC;

    /**
     * TODO
     * 冲红原因 冲红时填写，由企业定义
     *
     * length = 200
     * required = false
     */
    private String CHYY;

    /**
     * TODO
     * 价税合计金额
     * 小数点后2 位，以元为单位精确到分
     *
     * length = 16
     * required = true
     */
    @NonNull
    private String KPHJJE;

    /**
     * TODO
     * 合计不含税金额。所有商品行不含税金额之和。
     * 小数点后2 位，以元为单位精确到分（单行商品金额之和）。
     * 平台处理价税分离，此值传0
     *
     * length = 20
     * required = true
     */
    @NonNull
    private String HJBHSJE = "0";

    /**
     * TODO
     * 合计税额。所有商品行税额之和
     * 小数点后2 位，以元为单位精确到分（单行商品金额之和）。
     * 台处理价税分离，此值传0
     *
     * length = 20
     * required = true
     */
    @NonNull
    private String HJSE;

    /**
     * TODO
     * 备注
     * 增值税发票红字发票开具时，备注要求:
     * 开具负数发票，必须在备注中注明
     * “对应正数发票代码:XXXXXXXXX 号码:YYYYYYYY”字样，其中“X”为发票代码，“Y”为发票号码。
     *
     * length = 200
     * required = false
     */
    private String BZ;

    /**
     * 备用字段1
     * 该字段传输企业id，多个企业,id 以分号分隔
     */
    private String BYZD1;

    /**
     * 备用字段2
     * 如果企业一个纳税人识别号下有门店号（酒店id），则该字段传输门店号（酒店id）
     */
    private String BYZD2;

    /**
     * 备用字段3
     * {”sl”:” 业务标识”}业务标识：2：平台对接；3：扫码对接
     */
    private String BYZD3;

    /**
     * 备用字段4
     */
    private String BYZD4;

    /**
     * 备用字段5
     */
    private String BYZD5;



}
