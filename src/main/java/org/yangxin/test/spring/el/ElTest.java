package org.yangxin.test.spring.el;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author yangxin
 * 2022/9/3 15:18
 */
public class ElTest {

    public static void main(String[] args) throws NoSuchMethodException {
//        test1();
        test2("test2");
    }

    /**
     * 使用el表达式，动态组装参数
     * @param a 入参
     * @throws NoSuchMethodException 异常
     */
    private static void test2(String a) throws NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        EvaluationContext context = new StandardEvaluationContext();

        Method method = ElTest.class.getDeclaredMethod("test2", String.class);
        String[] paramArray = Optional.ofNullable(discoverer.getParameterNames(method)).orElse(new String[0]);
        List<String> valueList = Collections.singletonList(a);
        for (int i = 0; i < paramArray.length; i++) {
            context.setVariable(paramArray[i], valueList.get(i));
        }

        Expression expression = parser.parseExpression("#a.toString().concat('_').concat(#a)");
        String result = expression.getValue(context, String.class);
        System.out.println(result);
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
