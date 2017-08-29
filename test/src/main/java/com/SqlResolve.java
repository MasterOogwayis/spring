package com;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author ZhangShaowei on 2017/8/14 10:45
 */

public class SqlResolve {

    public static void sqlParser(final String sql) throws JSQLParserException {
//        CCJSqlParserManager parserManager = new CCJSqlParserManager();
//        Statement statement = parserManager.parse(new StringReader(sql));
        Statement statement = CCJSqlParserUtil.parse(sql);
        if (statement instanceof Select) {
            Select selectStatement = (Select) statement;
            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
            List<String> tableList = tablesNamesFinder.getTableList(selectStatement);
            tableList.forEach(System.out::println);

            PlainSelect plainSelect = (PlainSelect) selectStatement.getSelectBody();

//            SelectUtils.addExpression(selectStatement, new Column("TIME"));
//            plainSelect.getSelectItems().add(new SelectExpressionItem(new Column("Date")));
//            plainSelect.getSelectItems().forEach(System.out::println);

//            plainSelect.getGroupByColumnReferences().forEach(System.out::println);
//            plainSelect.getOrderByElements().forEach(System.out::println);
//            System.err.println("---------" + plainSelect.getLimit());
//            plainSelect.getGroupByColumnReferences().add(CCJSqlParserUtil.parseExpression("l.ID"));

//            Expression where = plainSelect.getWhere();
//            System.err.println("where: " + where);
//            Expression expr = CCJSqlParserUtil.parseExpression("a+b");
//            Addition addition = (Addition) expr;
//            System.err.println(addition.getLeftExpression());
//            System.err.println(addition.getRightExpression());
//
//            EqualsTo equalsTo = new EqualsTo();
//            equalsTo.setLeftExpression(addition);
//            equalsTo.setRightExpression(new LongValue(10L));
//
//            plainSelect.setWhere(equalsTo);

//            EqualsTo equalsTo = new EqualsTo();
//            equalsTo.setLeftExpression(new Column("id"));
//            equalsTo.setRightExpression(new TimestampValue(" 2016-10-11 12:12:12 "));

//            GreaterThan greaterThan = new GreaterThan();
//            greaterThan.setLeftExpression(new Column("CREATE_TIMESTAMP"));
//            greaterThan.setRightExpression(new TimestampValue(" 2016-10-11 12:12:12 "));

//            LikeExpression likeExpression = new LikeExpression();
//            likeExpression.setLeftExpression(new Column("NAME"));
//            likeExpression.setRightExpression(new StringValue("%asd%"));

//            IsNullExpression isNullExpression = new IsNullExpression();
//            isNullExpression.setLeftExpression(new Column("NAME"));
//            isNullExpression.setNot(true);

//            InExpression inExpression = new InExpression();
//            inExpression.setLeftExpression(new Column("NAME"));
//            ExpressionList expressionList = new ExpressionList();
//            expressionList.setExpressions(Arrays.asList(new StringValue(("123")), new StringValue(("456"))));
//            inExpression.setRightItemsList(expressionList);

//            if (Objects.isNull(where)) {
//                plainSelect.setWhere(greaterThan);
//            } else {
//                AndExpression and = new AndExpression(where, greaterThan);
//                plainSelect.setWhere(and);
//            }
//            System.err.println(selectStatement.getSelectBody());

//            ExpressionList expressionList = new ExpressionList();
//            List<Expression> expressions = new ArrayList<>();
//            Arrays.asList("1,2,3".split(",")).forEach(str -> expressions.add(new StringValue(str)));
//            expressionList.setExpressions(expressions);
//            InExpression inExpression = new InExpression(new Column("ID"), expressionList);
            InExpression inExpression = (InExpression) CCJSqlParserUtil.parseCondExpression(
                    String.format("%s %s(%s)", "NAME", "NOT IN", "'1','2','3'")
            );
            System.err.println(inExpression.getClass());

            plainSelect.setWhere(inExpression);

//            OrderByElement orderByElement = new OrderByElement();
//            orderByElement.setExpression(new Column("NAME"));
//            orderByElement.setAscDescPresent(true);
//            plainSelect.setOrderByElements(Arrays.asList(orderByElement));


            System.err.println(plainSelect);

        } else if (statement instanceof Insert) {
            System.err.println("Insert");
        } else if (statement instanceof Update) {
            System.err.println("Update");
        } else if (statement instanceof Delete) {
            System.err.println("Delete");
        }

    }

    public static void main(String[] args) throws Exception {

        String sql = "SELECT ID FROM TEST";

//        String sql = StringUtils.join(
//                Files.readAllLines(
//                        Paths.get("C:\\Users\\ZhangShaowei\\Desktop", "sql1.sql"),
//                        Charset.forName("UTF-8")), " ");
//        System.err.println(sql);

        try {
            sqlParser(sql);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
    }


}
