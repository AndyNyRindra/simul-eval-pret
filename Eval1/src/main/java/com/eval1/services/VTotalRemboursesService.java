package com.eval1.services;

import com.eval1.repositories.VTotalRemboursesRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.Double;
import java.lang.Long;
import java.math.BigDecimal;
import com.eval1.models.VTotalRembourses;


@Service
public class VTotalRemboursesService extends CrudService<VTotalRembourses, VTotalRemboursesRepo> {

    public VTotalRemboursesService(VTotalRemboursesRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VTotalRembourses> getEntityClass() {
        return VTotalRembourses.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

    @Override
    public int getPageSize() {
        return 12;
    }
}
