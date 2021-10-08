package com.zsw.demo;

import com.alibaba.excel.EasyExcel;
import com.zsw.pojo.DriverCostDailyCell;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/10/8 10:36
 */
public class TestWrite {

    @Test
    public void test() {
        String path = "C:\\Users\\ZhangShaowei\\Desktop\\2.xlsx";
        EasyExcel.write(path, DriverCostDailyCell.class)
                .sheet()
//                .excludeColumnFiledNames(Collections.singletonList("seatNum"))
                .doWrite(TestWrite::data);
    }


    private static List<DriverCostDailyCell> data() {
        DriverCostDailyCell cell = new DriverCostDailyCell();
        cell.setChangeVehicle("川A00000");
        cell.setFuelType("燃气");
        cell.setName("name");
        cell.setPhone("110");
        cell.setSeatNum(7);
        cell.setDate(LocalDateTime.now());
        return Collections.singletonList(cell);
    }

}
