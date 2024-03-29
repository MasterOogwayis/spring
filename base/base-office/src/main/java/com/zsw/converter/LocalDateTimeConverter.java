package com.zsw.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2021/8/3 10:53
 */
public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    private static final LocalDateTime BASE = Year.of(1900).atMonth(1).atDay(1).atStartOfDay();

    private static final BigDecimal SECOND_OF_DAY = BigDecimal.valueOf(TimeUnit.DAYS.toSeconds(1));

    @Override
    public Class<?> supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public LocalDateTime convertToJavaData(ReadCellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        BigDecimal numberValue = cellData.getNumberValue();
        return BASE.plusDays(numberValue.longValue())
                .plusSeconds(numberValue.remainder(BigDecimal.ONE).multiply(SECOND_OF_DAY).longValue());

    }

    @Override
    public WriteCellData<?> convertToExcelData(LocalDateTime value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new WriteCellData(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
