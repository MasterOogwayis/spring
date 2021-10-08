package com.zsw.office.excel.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.zsw.office.excel.IExcelService;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * doc https://www.yuque.com/easyexcel/doc/quickstart
 *
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
    public <T extends Serializable> void readAll(
            Path path, Class<T> clazz, ReadListener<T> listener, Integer headRowNumber) {
        EasyExcel.read(path.toFile(), clazz, listener)
                .headRowNumber(headRowNumber)
                .doReadAll();
    }

    @Override
    public <T extends Serializable> void readAll(
            InputStream inputStream, Class<T> clazz, ReadListener<T> listener, Integer headRowNumber) {
        EasyExcel.read(inputStream, clazz, listener)
                .headRowNumber(headRowNumber)
                .doReadAll();
    }

    @Override
    public <T extends Serializable> void write(
            List<T> data, Path path, Class<T> clazz, Integer sheetNo, String sheetName) {
        EasyExcel.write(path.toFile(), clazz)
                .sheet(sheetNo, sheetName)
                .doWrite(data);
    }

    @Override
    public <T extends Serializable> void write(
            List<T> data, OutputStream outputStream, Class<T> clazz, Integer sheetNo, String sheetName) {
        EasyExcel.write(outputStream, clazz)
                .sheet(sheetNo, sheetName)
                .doWrite(data);

    }

    @Override
    public <T extends Serializable> void write(
            Supplier<Collection<T>> data, Path path, Class<T> clazz, Integer sheetNo, String sheetName) {
        EasyExcel.write(path.toFile(), clazz)
                .sheet(sheetNo, sheetName)
                .doWrite(data::get);
    }

    @Override
    public <T extends Serializable> void write(
            Supplier<Collection<T>> data, OutputStream outputStream, Class<T> clazz, Integer sheetNo, String sheetName) {
        EasyExcel.write(outputStream, clazz)
                .sheet(sheetNo, sheetName)
                .doWrite(data::get);

    }
}
