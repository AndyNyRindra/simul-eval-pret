package com.eval1.services;

import com.eval1.repositories.ReimbursementStatusRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.loan.ReimbursementStatus;


@Service
public class ReimbursementStatusService extends CrudService<ReimbursementStatus, ReimbursementStatusRepo> {

    public ReimbursementStatusService(ReimbursementStatusRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<ReimbursementStatus> getEntityClass() {
        return ReimbursementStatus.class;
    }

}
