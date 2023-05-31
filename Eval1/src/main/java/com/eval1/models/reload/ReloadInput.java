package com.eval1.models.reload;

import custom.springutils.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReloadInput {

    private String date;
    private String amount;

    public ReloadRequest getReloadRequest() throws CustomException {
        ReloadRequest reloadRequest = new ReloadRequest();
        reloadRequest.setDate(java.sql.Date.valueOf(date));
        reloadRequest.setAmount(Double.valueOf(amount));
        return reloadRequest;
    }
}
