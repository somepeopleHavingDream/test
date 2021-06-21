package org.yangxin.test.ddd;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author yangxin
 * 2021/6/21 18:02
 */
@Value
public class Money {

    BigDecimal amount;

    Currency currency;

    public void pay(Money money, Long recipientId) {
        BankService.transfer(money, recipientId);
    }

    public static void main(String[] args) {
        Money money = new Money(null, null);
        money.pay(null, null);
    }
}
