package com.zsw.demo;

import com.alibaba.excel.EasyExcel;
import com.zsw.pojo.ComplexHeadData;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/20 15:59
 */
public class HeaderTests {

    public static void main(String[] args) {
        String path = "C:\\Users\\ZhangShaowei\\Desktop\\header.xlsx";
        EasyExcel.write(Paths.get(path).toFile())
                .head(head())
                .sheet(0)
                .sheetName("2021-8-20")
                .doWrite(Collections.emptyList());
    }


    public static List<List<String>> head() {
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("序号", "序号"));
        list.add(Arrays.asList("日期", "日期"));
        list.add(Arrays.asList("专线/快线", "专线/快线"));
        list.add(Arrays.asList("车队", "车队"));
        list.add(Arrays.asList("运营性质", "运营性质"));
        list.add(Arrays.asList("车牌", "车牌"));
        list.add(Arrays.asList("姓名", "姓名"));
        list.add(Arrays.asList("联系电话", "联系电话"));
        list.add(Arrays.asList("燃料类型", "燃料类型"));
        list.add(Arrays.asList("座位数", "座位数"));
        list.add(Arrays.asList("是否换车", "是否换车"));
        list.add(Arrays.asList("运营边数", "运营边数"));

        // 边数
        for (int i = 1; i <= 1; i++) {
            String firstTitle = "第" + i + "边";
            list.add(Arrays.asList(firstTitle, "快"));
            list.add(Arrays.asList(firstTitle, "拼"));
            list.add(Arrays.asList(firstTitle, "包"));
            list.add(Arrays.asList(firstTitle, "货"));
            list.add(Arrays.asList(firstTitle, "空"));
            list.add(Arrays.asList(firstTitle, "快金"));
            list.add(Arrays.asList(firstTitle, "专金"));
        }

        // 统计
        list.add(Arrays.asList("", "快"));
        list.add(Arrays.asList("", "拼"));
        list.add(Arrays.asList("", "包"));
        list.add(Arrays.asList("", "货"));
        list.add(Arrays.asList("", "放空"));
        list.add(Arrays.asList("", "快金"));
        list.add(Arrays.asList("", "专金"));
        list.add(Arrays.asList("", "金额"));
        list.add(Arrays.asList("", "订单数"));

        return list;
    }

    private static List<ComplexHeadData> data() {
        ComplexHeadData complexHeadData = new ComplexHeadData();
        complexHeadData.setString0("String0");
        complexHeadData.setString1("String1");
        complexHeadData.setString2("String2");
        complexHeadData.setString3("String3");
        complexHeadData.setString4("String4");

        return Collections.singletonList(complexHeadData);
    }

}
