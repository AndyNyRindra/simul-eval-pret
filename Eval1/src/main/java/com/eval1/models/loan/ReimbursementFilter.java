package com.eval1.models.loan;

import com.eval1.models.appUser.AppUser;
import custom.springutils.search.FilterObject;
import custom.springutils.search.OrderMethod;
import custom.springutils.search.map.Filter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReimbursementFilter extends FilterObject {

    private Loan loan;

    public ReimbursementFilter() {
        setField("month");
        setMethod(OrderMethod.ASC);
    }

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();

        filterConditions.append("");

        return filterConditions.toString().replace("%", "");
    }
}
