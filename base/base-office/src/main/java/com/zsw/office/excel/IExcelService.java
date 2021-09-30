package com.zsw.office.excel;

import com.alibaba.excel.read.listener.ReadListener;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/9/30 15:23
 */
public interface IExcelService {

    <T extends Serializable> void read(
            Path path, Class<T> clazz, ReadListener<T> listener,
            Integer sheetNo, String sheetName, Integer headRowNumber);

    <T extends Serializable> void read(
            InputStream inputStream, Class<T> clazz, ReadListener<T> listener,
            Integer sheetNo, String sheetName, Integer headRowNumber);


    <T extends Serializable> void write(List<T> data, Path path, Integer sheetNo, String sheetName);

    <T extends Serializable> void write(List<T> data, OutputStream outputStream, Integer sheetNo, String sheetName);


}
