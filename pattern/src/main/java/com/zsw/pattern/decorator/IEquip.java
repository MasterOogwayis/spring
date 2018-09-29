package com.zsw.pattern.decorator;

/**
 * 装饰模式
 * 装备的接口
 * 下面有:武器,护腕,鞋子,戒指、、、、还有装饰品接口
 *
 * @author ZhangShaowei on 2018/9/25 10:12
 **/
public interface IEquip {

    /**
     * 计算攻击力
     *
     * @return
     */
    int caculateAttack();

    /**
     * 装备的描述
     *
     * @return
     */
    String description();


}
