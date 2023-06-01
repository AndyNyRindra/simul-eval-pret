package com.eval1.services;

import com.eval1.models.Movement;
import com.eval1.models.appUser.AppUser;
import com.eval1.models.loan.ReimbursementStatus;
import com.eval1.repositories.ReimbursementRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eval1.models.loan.Reimbursement;

import java.sql.Date;
import java.util.List;


@Service
public class ReimbursementService extends CrudService<Reimbursement, ReimbursementRepo> {

    @Autowired
    private MovementService movementService;

    @Autowired
    private VSoldeService vSoldeService;

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

    @Transactional(rollbackOn = Exception.class)
    public Reimbursement pay(Long id, Date date) throws Exception {
        Reimbursement reimbursement = findById(id);
        ReimbursementStatus status = new ReimbursementStatus();
        status.setId(10L);
        reimbursement.setStatus(status);
        reimbursement.setDate(date);

        AppUser appUser  = reimbursement.getLoan().getRequest().getClient();
        if (vSoldeService.getSolde(appUser.getId()) < reimbursement.getAmount()) {
            throw new CustomException("Solde insuffisant");
        }
        Movement movement = new Movement();
        movement.setAmount(reimbursement.getTotal());
        movement.setClientId(appUser.getId().intValue());
        movement.setMoveTypeId(10);
        movement.setDate(date);
        movementService.create(movement);
        return super.update(reimbursement);
    }

}
