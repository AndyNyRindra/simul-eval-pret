package com.eval1.services;

import com.eval1.models.Movement;
import com.eval1.models.Status;
import com.eval1.repositories.ReloadRequestRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eval1.models.reload.ReloadRequest;

import java.sql.Date;
import java.time.LocalDate;


@Service
public class ReloadRequestService extends CrudService<ReloadRequest, ReloadRequestRepo> {

    @Autowired
    private MovementService movementService;

    public ReloadRequestService(ReloadRequestRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<ReloadRequest> getEntityClass() {
        return ReloadRequest.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }


    @Override
    public ReloadRequest create(ReloadRequest obj) throws Exception {
        Status status = new Status();
        status.setId(0L);
        obj.setStatus(status);
        return super.create(obj);
    }

    public ReloadRequest updateStatus(Long id, Long statusId) throws Exception {
        ReloadRequest obj = findById(id);
        Status status = new Status();
        status.setId(statusId);
        obj.setStatus(status);
        return super.update(obj);
    }

    @Transactional(rollbackOn = {Exception.class})
    public ReloadRequest accept(Long id, Date date) throws Exception {
        ReloadRequest reloadRequest = updateStatus(id, 20L);
        Movement movement = new Movement();
        movement.setAmount(reloadRequest.getAmount());
        movement.setClientId(reloadRequest.getClient().getId().intValue());
        movement.setMoveTypeId(0);
        movement.setDate(date);
        movementService.create(movement);
        return reloadRequest;
    }
}
