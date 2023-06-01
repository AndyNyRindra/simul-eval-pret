package com.eval1.services;

import com.eval1.models.Movement;
import com.eval1.models.loan.Reimbursement;
import com.eval1.models.loan.ReimbursementStatus;
import com.eval1.models.Status;
import com.eval1.models.loan.Loan;
import com.eval1.models.maxAmount.MaxAmount;
import com.eval1.models.rate.Rate;
import com.eval1.repositories.LoanRequestRepo;
import com.eval1.repositories.MaxAmountRepo;
import com.eval1.repositories.RateRepo;
import custom.springutils.exception.CustomException;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eval1.models.loan.LoanRequest;

import java.sql.Date;
import java.time.LocalDate;


@Service
public class LoanRequestService extends CrudService<LoanRequest, LoanRequestRepo> {

    @Autowired
    private MaxAmountRepo maxAmountRepo;

    @Autowired
    private RateRepo rateRepo;

    @Autowired
    private MovementService movementService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private ReimbursementService reimbursementService;

    public LoanRequestService(LoanRequestRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<LoanRequest> getEntityClass() {
        return LoanRequest.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
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

    public LoanRequest updateStatus(Long id, Long statusId) throws Exception {
        LoanRequest obj = findById(id);
        Status status = new Status();
        status.setId(statusId);
        obj.setStatus(status);
        return super.update(obj);
    }

    public Date[] getNextMonths(Date date, int count) {
        Date[] dates = new Date[count];
        dates[0] = date;
        for (int i = 1; i < count; i++) {
            LocalDate localDate = date.toLocalDate().plusMonths(i);
            dates[i] = Date.valueOf(localDate);

        }
        return dates;
    }

    @Transactional(rollbackOn = {Exception.class})
    public LoanRequest accept(Long id, Date date, Date startReimbursement) throws Exception {
        LoanRequest loanRequest = updateStatus(id, 20L);
        super.update(loanRequest);
        Loan loan = new Loan();
        loan.setRequest(loanRequest);
        loan.setDate(date);
        loan.setStartReimbursement(startReimbursement);
        loanService.create(loan);
        Movement movement = new Movement();
        movement.setAmount(loanRequest.getAmount());
        movement.setClientId(loanRequest.getClient().getId().intValue());
        movement.setMoveTypeId(0);
        movement.setDate(date);
        movementService.create(movement);
        Date[] months = getNextMonths(startReimbursement, loanRequest.getDuration().getDuration().intValue());
        double remains = loanRequest.getAmount();
        int count = months.length;
        for (int i = 0; i < months.length; i++) {
            Reimbursement reimbursement = new Reimbursement();
            reimbursement.setLoan(loan);
            reimbursement.setMonth(months[i]);
            reimbursement.setRemains(remains);
            remains = remains - (remains/count);
            count --;
            reimbursement.setAmount(loanRequest.getAmount() / (double)months.length);
            reimbursement.setRate();
            reimbursement.setTotal();
            ReimbursementStatus reimbursementStatus = new ReimbursementStatus();
            reimbursementStatus.setId(0L);
            reimbursement.setStatus(reimbursementStatus);
            reimbursementService.create(reimbursement);
        }
        return loanRequest;
    }
}
