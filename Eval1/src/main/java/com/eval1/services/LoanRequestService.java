package com.eval1.services;

import com.eval1.models.Status;
import com.eval1.models.maxAmount.MaxAmount;
import com.eval1.models.rate.Rate;
import com.eval1.repositories.LoanRequestRepo;
import com.eval1.repositories.MaxAmountRepo;
import com.eval1.repositories.RateRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eval1.models.loan.LoanRequest;


@Service
public class LoanRequestService extends CrudService<LoanRequest, LoanRequestRepo> {

    @Autowired
    private MaxAmountRepo maxAmountRepo;

    @Autowired
    private RateRepo rateRepo;

    public LoanRequestService(LoanRequestRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<LoanRequest> getEntityClass() {
        return LoanRequest.class;
    }

    public Boolean isRequestValid(LoanRequest loanRequest) throws CustomException {
        MaxAmount maxAmount = maxAmountRepo.findLast(loanRequest.getDate());
        if (maxAmount != null && loanRequest.getAmount() > maxAmount.getAmount()) {
            throw new CustomException("Le montant demandé est supérieur au montant maximum autorisé");
        }
        return true;
    }

    @Override
    public LoanRequest create(LoanRequest obj) throws Exception {
        if (isRequestValid(obj)) {
            Status status = new Status();
            status.setId(0L);
            obj.setStatus(status);
            Rate rate = rateRepo.findLast(obj.getDate());
            obj.setRate(rate.getRate());
            return super.create(obj);
        }
        return null;
    }
}
