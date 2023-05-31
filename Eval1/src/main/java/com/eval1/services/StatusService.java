package com.eval1.services;

import com.eval1.repositories.StatusRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import com.eval1.models.Status;


@Service
public class StatusService extends CrudService<Status, StatusRepo> {

    public StatusService(StatusRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Status> getEntityClass() {
        return Status.class;
    }

}
