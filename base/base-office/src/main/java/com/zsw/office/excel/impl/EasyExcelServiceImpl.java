package com.zsw.office.excel.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.zsw.converter.LocalDateTimeConverter;
import com.zsw.office.excel.IExcelService;
import com.zsw.pojo.IndexOrNameData;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/9/30 15:28
 */
public class EasyExcelServiceImpl implements IExcelService {
    @Override
    public <T extends Serializable> void read(
            Path path, Class<T> clazz, ReadListener<T> listener,
            Integer sheetNo, String sheetName, Integer headRowNumber) {
        EasyExcel.read(path.toFile(), clazz, listener)
                .sheet(sheetNo, sheetName)
                .headRowNumber(headRowNumber)
                .doRead();
    }

    @Override
    public <T extends Serializable> void read(
            InputStream inputStream, Class<T> clazz, ReadListener<T> listener,
            Integer sheetNo, String sheetName, Integer headRowNumber) {
        EasyExcel.read(inputStream, clazz, listener)
                .sheet(sheetNo, sheetName)
                .headRowNumber(headRowNumber)
                .doRead();
    }

    @Override
    public <T extends Serializable> void write(List<T> data, Path path, Integer sheetNo, String sheetName) {
        EasyExcel.write(path.toFile(), IndexOrNameData.class)
                .sheet(sheetNo, sheetName)
                .doWrite(data);
    }

    @Override
    public <T extends Serializable> void write(List<T> data, OutputStream outputStream, Integer sheetNo, String sheetName) {
        EasyExcel.write(outputStream, IndexOrNameData.class)
                .sheet(sheetNo, sheetName)
                .doWrite(data);
    }
}
