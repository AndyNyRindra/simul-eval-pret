package com.eval1.models.loan;

import com.eval1.models.Status;
import custom.springutils.search.FilterObject;
import custom.springutils.search.OrderMethod;
import custom.springutils.search.map.Filter;
import custom.springutils.search.map.IgnoreMapping;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanFilter extends FilterObject {

    private Status status;

    @IgnoreMapping
    private Long statusId;

    @Filter("ilike_client.name")
    private String client;

    private Double mineq_amount;

    private Double maxeq_amount;

    public LoanFilter() {
        setField("date");
        setMethod(OrderMethod.DESC);
    }

    public void setStatusId(Long statusId) {
        if (statusId != null) {
            this.statusId = statusId;
            Status status1 = new Status();
            status1.setId(statusId);
            setStatus(status1);
        }
    }

    public void setClient(String client) {
        this.client = "%" + client + "%";
    }

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("statusId");
        if (getStatusId() != null) {
            filterConditions.append("=" +getStatusId());
        }
        filterConditions.append("&client");
        if (getClient() != null) {
            filterConditions.append("=" +getClient());
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
