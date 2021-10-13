package com.zsw.office.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
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
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        super.invokeHead(headMap, context);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        super.onException(exception, context);
    }
}
