package com.eval1.services;

import com.eval1.repositories.ReimbursementRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.loan.Reimbursement;

import java.util.List;


@Service
public class ReimbursementService extends CrudService<Reimbursement, ReimbursementRepo> {

    public ReimbursementService(ReimbursementRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Reimbursement> getEntityClass() {
        return Reimbursement.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

    public Double getSumRate(List<Reimbursement> reimbursements) {
        Double sum = 0.0;
        for (Reimbursement reimbursement : reimbursements) {
            sum += reimbursement.getRate();
        }
        return sum;
    }

}
