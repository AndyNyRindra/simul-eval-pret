package com.eval1.services;

import com.eval1.repositories.LoanRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.loan.Loan;


@Service
public class LoanService extends CrudService<Loan, LoanRepo> {

    public LoanService(LoanRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


}
