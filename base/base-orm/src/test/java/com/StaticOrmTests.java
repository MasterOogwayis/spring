package com;

import lombok.SneakyThrows;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;

/**
 * @author Shaowei Zhang on 2019/4/21 0:06
 **/
public class StaticOrmTests {


    @SneakyThrows
    public static void main(String[] args) {
        Statement statement = CCJSqlParserUtil.parse("update t_customer set name='Shaowei Zhang' where id = 1");

        if (statement instanceof Select) {

        }

        System.out.println(statement.getClass());

    }

}
