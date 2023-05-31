package com.eval1.models.maxAmount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaxAmountInput {

        private String date;
        private String amount;

        public MaxAmount getMaxAmount () {
            MaxAmount maxAmount = new MaxAmount();
            maxAmount.setDate(java.sql.Date.valueOf(this.date));
            maxAmount.setAmount(Double.parseDouble(this.amount));
            return maxAmount;
        }
}
