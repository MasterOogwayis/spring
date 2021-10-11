package com.zsw.demo;

import com.alibaba.excel.EasyExcel;
import com.zsw.demo.index.ExcelData;
import com.zsw.office.excel.IExcelService;
import com.zsw.office.excel.impl.EasyExcelServiceImpl;
import com.zsw.office.excel.listener.ConsumerDataListener;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZhangShaowei on 2021/9/30 15:05
 */
public class TestRead {

    @Test
    public void test() {
        String path = "C:\\Users\\ZhangShaowei\\Desktop\\1.xlsx";
        List<ExcelData> list = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        EasyExcel.read(
                Paths.get(path).toFile(),
                ExcelData.class,
                new ConsumerDataListener<ExcelData>(d -> {
//                    if (counter.incrementAndGet() % 20 == 0) {
                    // 这样中断 list 会包含 20 条数据，其他异常直接往外抛
//                        throw new ExcelAnalysisStopException("中断");
//                    }
                    list.add(d);
                }))
                .headRowNumber(4)
                .doReadAll();

        list.forEach(System.out::println);
    }

    @Test
    public void test1() {
        IExcelService excelService = new EasyExcelServiceImpl();
        String path = "/data/1.xlsx";
        List<ExcelData> list = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);
        excelService.read(Paths.get(path), ExcelData.class, new ConsumerDataListener<ExcelData>(d -> {
//                    if (counter.incrementAndGet() % 20 == 0) {
            // 这样中断 list 会包含 20 条数据，其他异常直接往外抛
//                        throw new ExcelAnalysisStopException("中断");
//                    }
            list.add(d);
        }), null, null, 4);
        list.forEach(System.out::println);
    }


}
