package org.yangxin.test.spring.expression.el;

import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangxin
 * 2022/9/3 15:12
 */
@SuppressWarnings("unused")
public class ElUtil {

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    private static final Map<String, Expression> CACHE = new ConcurrentHashMap<>();

    /**
     * 获取解析后的表达式
     *
     * @param expression el表达式字符串
     * @return 解析后的表达式，如果之前已经解析过，则返回缓存的表达式
     */
    public static Expression getExpression(String expression) {
        if (StringUtils.isBlank(expression)) {
            return null;
        }

        expression = expression.trim();
        return CACHE.computeIfAbsent(expression, PARSER::parseExpression);
    }
}
