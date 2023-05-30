package com.eval1.services;

import com.eval1.repositories.MaxDurationRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Date;
import java.lang.Integer;
import com.eval1.models.MaxDuration;


@Service
public class MaxDurationService extends CrudService<MaxDuration, MaxDurationRepo> {

    public MaxDurationService(MaxDurationRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<MaxDuration> getEntityClass() {
        return MaxDuration.class;
    }

}
