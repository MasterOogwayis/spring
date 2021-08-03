package com.zsw.office.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * @author ZhangShaowei on 2021/8/3 10:41
 */
@Data
@AllArgsConstructor
public class ConsumerDataListener<T extends Serializable> extends AnalysisEventListener<T> {

    private final Consumer<T> consumer;

    @Override
    public void invoke(T data, AnalysisContext context) {
        this.consumer.accept(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
