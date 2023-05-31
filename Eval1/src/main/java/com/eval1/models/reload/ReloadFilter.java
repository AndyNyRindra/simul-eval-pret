package com.eval1.models.reload;

import com.eval1.models.Status;
import custom.springutils.search.FilterObject;
import custom.springutils.search.OrderMethod;
import custom.springutils.search.map.Filter;
import custom.springutils.search.map.IgnoreMapping;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReloadFilter extends FilterObject {

    private Status status;

    @IgnoreMapping
    private Long statusId;

    @Filter("ilike_client.name")
    private String clientName;

    private Double mineq_amount;

    private Double maxeq_amount;

    public ReloadFilter() {
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

    public void setClientName(String client) {
        this.clientName = "%" + client + "%";
    }

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("statusId");
        if (getStatusId() != null) {
            filterConditions.append("=" +getStatusId());
        }
        filterConditions.append("&clientName");
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
