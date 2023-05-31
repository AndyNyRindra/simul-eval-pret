package com.eval1.services;

import com.eval1.repositories.VSoldeRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.Double;
import java.lang.Long;
import java.lang.Integer;
import com.eval1.models.VSolde;


@Service
public class VSoldeService extends CrudService<VSolde, VSoldeRepo> {

    public VSoldeService(VSoldeRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VSolde> getEntityClass() {
        return VSolde.class;
    }

    public Double getSolde(Long clientId) {
        if (repo.findByClientId(clientId) != null) {
            return repo.findByClientId(clientId).getSolde();
        }
        return 0.0;
    }

}
