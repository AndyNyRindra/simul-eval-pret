package com.eval1.models.loan;

import com.eval1.models.appUser.AppUser;
import custom.springutils.search.FilterObject;
import custom.springutils.search.OrderMethod;
import custom.springutils.search.map.Filter;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoanFilter extends FilterObject {

    @Filter("request.client")
    private AppUser client;

    @Filter("ilike_request.client.name")
    private String clientName;

    @Filter("mineq_request.amount")
    private Double mineq_amount;

    @Filter("maxeq_request.amount")
    private Double maxeq_amount;

    public LoanFilter() {
        setField("date");
        setMethod(OrderMethod.DESC);
    }

    public void setClientName(String client) {
        this.clientName = "%" + client + "%";
    }

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();

        filterConditions.append("clientName");
        if (getClientName() != null) {
            filterConditions.append("=" +getClientName());
        }
        filterConditions.append("&mineq_amount");
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
