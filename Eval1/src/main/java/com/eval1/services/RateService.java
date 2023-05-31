package com.eval1.services;

import com.eval1.repositories.RateRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.rate.Rate;


@Service
public class RateService extends CrudService<Rate, RateRepo> {

    public RateService(RateRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Rate> getEntityClass() {
        return Rate.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


}
