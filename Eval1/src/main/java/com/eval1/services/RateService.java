package com.eval1.services;

import com.eval1.repositories.RateRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.math.BigDecimal;
import java.lang.Integer;
import com.eval1.models.Rate;


@Service
public class RateService extends CrudService<Rate, RateRepo> {

    public RateService(RateRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Rate> getEntityClass() {
        return Rate.class;
    }

}
