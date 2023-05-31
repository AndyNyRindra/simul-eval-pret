package com.eval1.models.duration;

import custom.springutils.search.FilterObject;
import custom.springutils.search.OrderMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DurationFilter extends FilterObject {

    private Double mineq_duration;
    private Double maxeq_duration;

    public DurationFilter() {
        setField("duration");
        setMethod(OrderMethod.ASC);
    }


    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("mineq_duration");
        if (getMineq_duration() != null) {
            filterConditions.append("=" +getMineq_duration());
        }
        filterConditions.append("&maxeq_duration");
        if (getMaxeq_duration() != null) {
            filterConditions.append("=" +getMaxeq_duration());
        }

        return filterConditions.toString().replace("%", "");
    }
}
