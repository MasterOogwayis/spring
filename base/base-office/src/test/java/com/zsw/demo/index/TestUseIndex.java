package com.zsw.demo.index;

import com.alibaba.excel.EasyExcel;
import com.zsw.office.excel.listener.ConsumerDataListener;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/9/30 15:05
 */
public class TestUseIndex {

    public static void main(String[] args) {
        String path = "C:\\Users\\ZhangShaowei\\Desktop\\1.xlsx";
        List<ExcelData> list = new ArrayList<>();
        EasyExcel.read(Paths.get(path).toFile(), ExcelData.class, new ConsumerDataListener<ExcelData>(list::add))
                .sheet()
                .headRowNumber(4)
                .doRead();

        list.forEach(System.out::println);


    }


}
