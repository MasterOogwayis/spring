package com.zsw.office.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zsw.pojo.DriverProfit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * @author ZhangShaowei on 2021/8/3 10:19
 */
@Slf4j
@AllArgsConstructor
public class DriverProfitListener extends AnalysisEventListener<DriverProfit> {

    private final Consumer<DriverProfit> consumer;

    @Override
    public void invoke(DriverProfit data, AnalysisContext context) {
        this.consumer.accept(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("End reading file ...");
    }
}
