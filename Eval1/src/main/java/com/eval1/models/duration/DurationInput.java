package com.eval1.models.duration;

import custom.springutils.exception.CustomException;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DurationInput {

    private String duration;

    public Duration getDuration () throws CustomException {
        Duration duration = new Duration();
        duration.setDuration(Double.parseDouble(this.duration));
        duration.setDeleted(false);
        return duration;
    }
}
