package com.eval1.models.rate;

import custom.springutils.search.FilterObject;
import custom.springutils.search.OrderMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RateFilter extends FilterObject {

    private Double mineq_rate;
    private Double maxeq_rate;

    public RateFilter() {
        setField("date");
        setMethod(OrderMethod.DESC);
    }

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("mineq_rate");
        if (getMineq_rate() != null) {
            filterConditions.append("=" + getMineq_rate());
        }
        filterConditions.append("&maxeq_rate");
        if (getMaxeq_rate() != null) {
            filterConditions.append("=" + getMaxeq_rate());
        }

        return filterConditions.toString().replace("%", "");
    }
}
