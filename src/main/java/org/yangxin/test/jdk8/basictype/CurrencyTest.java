package org.yangxin.test.jdk8.basictype;

import java.util.Currency;
import java.util.Locale;

/**
 * @author yangxin
 * 2021/6/22 10:24
 */
public class CurrencyTest {

    public static void main(String[] args) {
        Currency currency = Currency.getInstance(Locale.US);
        System.out.println(currency.getSymbol());
        System.out.println(currency.getDefaultFractionDigits());
    }
}
