package com;


import org.apache.commons.collections.functors.ExceptionFactory;

import java.util.*;

/**
 * @author ZhangShaowei on 2017/6/27 10:52
 */

public class Test {

    private static final String SQL_FORMAT = "%Y-%m-%d %H:%i:%s";

    public static void main(String[] args) throws Exception {

        String str = "123*(%NAME%-2)99".replace("%NAME%", "6");
        System.err.println(str);
        System.out.println(Calculator.conversion(str));


//        ExceptionFactory.getInstance().create();

//        Long first = System.currentTimeMillis();
//        for (int i = 0; i < 1000000; i++) {
//            String str = " STR_TO_DATE('" + "123" + "','%Y-%m-%d %H:%i:%s') ";
//        }
//        Long second = System.currentTimeMillis();
//        System.err.println("str:" + (second - first));
//
//        for (int i = 0; i < 1000000; i++) {
//            String str = String.format("STR_TO_DATE('%s','%s')", "123", SQL_FORMAT);
//        }
//
//        Long third = System.currentTimeMillis();
//        System.err.println("format:" + (third - second));
//
//        for (int i = 0; i < 1000000; i++) {
//            String str = new StringBuilder("STR_TO_DATE('%s','%s')").append("123").append(SQL_FORMAT).toString();
//        }
//
//        Long forth = System.currentTimeMillis();
//        System.err.println("builder:" + (forth - third));

//        String sql = "SELECT NAME, ADDRESS, COL1 FROM USER";
//        Select select = (Select) CCJSqlParserUtil.parse(sql);
//
//        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();


//        Expression exception = CCJSqlParserUtil.parseExpression("AGE age");


//        SelectExpressionItem selectExpressionItem = new SelectExpressionItem(new Column("AGE age"));
//        plainSelect.getSelectItems().add(selectExpressionItem);


//        System.out.println(exception.getClass());
//        Column column = (Column) exception;
//        System.out.println(column.getName(false));


//        System.err.println(plainSelect);


//        get(1);

    }


    /**
     *
     * @param value obj
     */
    private static void get(final Object value){
        System.out.println(value.getClass().equals(Integer.class));
    }


}
