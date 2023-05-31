package com.eval1.models.loan;

import com.eval1.models.duration.Duration;
import custom.springutils.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanInput {

    private String amount;
    private String date;
    private Integer durationId;

    public LoanRequest getLoanRequest() throws CustomException {
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setAmount(Double.parseDouble(amount));
        loanRequest.setDate(java.sql.Date.valueOf(date));
        Duration duration = new Duration();
        duration.setId(Long.valueOf(durationId));
        loanRequest.setDuration(duration);
        return loanRequest;
    }
}
