package org.yangxin.test.spring.el;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author yangxin
 * 2022/9/3 15:18
 */
public class ElTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        String expressionStr = "'hello world'.toUpperCase().substring(1, 5)";

        // 指定SpelExpressionParser解析器实现类
        SpelExpressionParser parser = new SpelExpressionParser();
        // 解析表达式
        Expression expression = parser.parseExpression(expressionStr);

        System.out.println(expression.getValue());
    }
}
