package com.eval1.services;

import com.eval1.repositories.DurationRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import com.eval1.models.Duration;


@Service
public class DurationService extends CrudService<Duration, DurationRepo> {

    public DurationService(DurationRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Duration> getEntityClass() {
        return Duration.class;
    }

}
