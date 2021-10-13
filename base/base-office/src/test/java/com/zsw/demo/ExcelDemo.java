package com.zsw.demo;

import com.alibaba.excel.EasyExcel;
import com.zsw.converter.LocalDateTimeConverter;
import com.zsw.office.listener.ConsumerDataListener;
import com.zsw.pojo.DriverProfit;
import com.zsw.pojo.IndexOrNameData;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/3 10:10
 */
public class ExcelDemo {


    public static void main(String[] args) {
        syncRead("C:\\Users\\ZhangShaowei\\Desktop\\demo.xlsx");
    }

    public static void syncRead(String path) {
        EasyExcel.read(Paths.get(path).toFile(), DriverProfit.class, null)
//                .headRowNumber(2)
                .sheet()
                .doReadSync()
                .forEach(System.out::println);
    }


    public static void read(String path) {
        List<DriverProfit> list = new ArrayList<>();
        EasyExcel.read(Paths.get(path).toFile(), DriverProfit.class, new ConsumerDataListener<DriverProfit>(list::add))
                .headRowNumber(2).sheet().doRead();
        list.forEach(System.out::println);
    }

    public static void readIndexNamed(String path) {
        List<IndexOrNameData> list = new ArrayList<>();
        EasyExcel.read(Paths.get(path).toFile(), IndexOrNameData.class, new ConsumerDataListener<IndexOrNameData>(list::add))
                .registerConverter(new LocalDateTimeConverter())
                .sheet()
                .doRead();
        list.forEach(System.out::println);
    }


}
