package com.eval1.services;

import com.eval1.repositories.MaxAmountRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.maxAmount.MaxAmount;


@Service
public class MaxAmountService extends CrudService<MaxAmount, MaxAmountRepo> {

    public MaxAmountService(MaxAmountRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<MaxAmount> getEntityClass() {
        return MaxAmount.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

}
