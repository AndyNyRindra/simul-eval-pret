package com.eval1.models.maxAmount;

import custom.springutils.search.FilterObject;
import custom.springutils.search.OrderMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MaxAmountFilter extends FilterObject {

    private Double mineq_amount;
    private Double maxeq_amount;

    public MaxAmountFilter() {
        setField("date");
        setMethod(OrderMethod.DESC);
    }

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("mineq_amount");
        if (getMineq_amount() != null) {
            filterConditions.append("=" +getMineq_amount());
        }
        filterConditions.append("&maxeq_amount");
        if (getMaxeq_amount() != null) {
            filterConditions.append("=" +getMaxeq_amount());
        }

        return filterConditions.toString().replace("%", "");
    }
}
