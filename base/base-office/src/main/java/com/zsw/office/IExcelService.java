package com.zsw.office;

import com.alibaba.excel.read.listener.ReadListener;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

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

    <T extends Serializable> void readAll(
            Path path, Class<T> clazz, ReadListener<T> listener, Integer headRowNumber);

    <T extends Serializable> void readAll(
            InputStream inputStream, Class<T> clazz, ReadListener<T> listener, Integer headRowNumber);


    <T extends Serializable> void write(
            List<T> data, Path path, Class<T> clazz, Integer sheetNo, String sheetName);

    <T extends Serializable> void write(
            List<T> data, OutputStream outputStream, Class<T> clazz, Integer sheetNo, String sheetName);

    <T extends Serializable> void write(
            Supplier<Collection<T>> data, Path path, Class<T> clazz, Integer sheetNo, String sheetName);

    <T extends Serializable> void write(
            Supplier<Collection<T>> data, OutputStream outputStream, Class<T> clazz, Integer sheetNo, String sheetName);

    void write(List<List<String>> head, List<?> data, Path path, Integer sheetNo, String sheetName);

    void write(List<List<String>> head, List<?> data, OutputStream outputStream, Integer sheetNo, String sheetName);

    void write(List<List<String>> head, Supplier<Collection<?>> data, Path path, Integer sheetNo, String sheetName);

    void write(List<List<String>> head, Supplier<Collection<?>> data, OutputStream outputStream, Integer sheetNo, String sheetName);


}
