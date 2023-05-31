package com.eval1.models.rate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateInput {

        private String date;
        private String rate;

        public Rate getRate () {
            Rate rate = new Rate();
            rate.setDate(java.sql.Date.valueOf(this.date));
            rate.setRate(Double.parseDouble(this.rate));
            return rate;
        }
}
