package com.avondock.core.shared.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
public class Money {

    BigDecimal amount;
    @Embedded
    Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money add(Money delta) {
        return new Money(amount.add(delta.amount));
    }

    public Money multiply(Integer n) {
        return new Money(amount.multiply(new BigDecimal(n)));
    }

    public String getCurrencyCode() {
        return currency.getCode();
    }
}
