package com.eval1.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MonthYearFilter {

    private Integer annee;
    private Integer mois;

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("annee");
        if (getAnnee() != null) {
            filterConditions.append("=" +getAnnee());
        }
        filterConditions.append("&mois");
        if (getMois() != null) {
            filterConditions.append("=" +getMois());
        }
        return filterConditions.toString();
    }
}
