package com.eval1.services;

import com.eval1.repositories.MovementRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.lang.Double;
import java.lang.Integer;
import com.eval1.models.Movement;


@Service
public class MovementService extends CrudService<Movement, MovementRepo> {

    public MovementService(MovementRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<Movement> getEntityClass() {
        return Movement.class;
    }

}
