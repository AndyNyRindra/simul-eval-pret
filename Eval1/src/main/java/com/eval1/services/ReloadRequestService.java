package com.eval1.services;

import com.eval1.models.Status;
import com.eval1.repositories.ReloadRequestRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.reload.ReloadRequest;


@Service
public class ReloadRequestService extends CrudService<ReloadRequest, ReloadRequestRepo> {

    public ReloadRequestService(ReloadRequestRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<ReloadRequest> getEntityClass() {
        return ReloadRequest.class;
    }

    @Override
    public ReloadRequest create(ReloadRequest obj) throws Exception {
        Status status = new Status();
        status.setId(0L);
        obj.setStatus(status);
        return super.create(obj);
    }
}
