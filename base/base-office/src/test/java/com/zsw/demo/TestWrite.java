package com.zsw.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.zsw.office.IExcelService;
import com.zsw.office.impl.EasyExcelServiceImpl;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.junit.Test;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/10/8 10:36
 */
public class TestWrite {

    @Test
    public void test() {
        String path = "/data/excel/2.xlsx";
        EasyExcel.write(path, DriverCostDailyCell.class)
                .sheet()
                // 忽略字段
//                .excludeColumnFiledNames(Collections.singletonList("seatNum"))
                .doWrite(TestWrite::data);
    }

    @Test
    public void test1() {
        String path = "/data/excel/2.xlsx";
        IExcelService excelService = new EasyExcelServiceImpl();
        excelService.write(TestWrite::data, Paths.get(path), DriverCostDailyCell.class, null, null);
    }

    @Test
    public void test2() {
        String path = "C:\\Users\\ZhangShaowei\\Desktop\\2.xlsx";
        IExcelService excelService = new EasyExcelServiceImpl();
        excelService.write(head(), TestWrite::simpleData, Paths.get(path), null, null);
    }

    @Test
    public void test3() {
        String path = "C:\\Users\\ZhangShaowei\\Desktop\\2.xlsx";
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为红色
//        headWriteCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)18);
        headWriteFont.setFontName("宋体");
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 水平对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 背景绿色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)12);
        contentWriteFont.setColor(Font.COLOR_NORMAL);
        contentWriteFont.setFontName("Microsoft Sans Serif");
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
        HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        EasyExcel.write(path)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .head(head())
                .sheet()
                .doWrite(TestWrite::simpleData);
    }

    private static List<List<String>> head() {
        List<List<String>> head = new ArrayList<>();
        head.add(Collections.singletonList("title1"));
        head.add(Collections.singletonList("title2"));
        head.add(Collections.singletonList("title3"));
        head.add(Collections.singletonList("title4"));
        return head;
    }

    private static List<List<Object>> simpleData() {
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("content1", 1, 1.123D, new Date()));
        data.add(Arrays.asList("content2", 2, 2.123D, new Date()));
        data.add(Arrays.asList("content3", 3, 3.123D, new Date()));
        data.add(Arrays.asList("content4", 4, 4.123D, new Date()));
        data.add(Arrays.asList("content5", 5, 5.123D, new Date()));
        return data;
    }


    private static List<DriverCostDailyCell> data() {
        DriverCostDailyCell cell = new DriverCostDailyCell();
        cell.setChangeVehicle("川A00000");
        cell.setFuelType("燃气");
        cell.setName("name");
        cell.setPhone("110");
        cell.setSeatNum(7);
        cell.setDate(LocalDateTime.now());
        cell.setAmount(123.4567D);
        return Collections.singletonList(cell);
    }

}
