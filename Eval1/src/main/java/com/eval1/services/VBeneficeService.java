package com.eval1.services;

import com.eval1.repositories.VBeneficeRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.Double;
import java.math.BigDecimal;
import com.eval1.models.VBenefice;


@Service
public class VBeneficeService extends CrudService<VBenefice, VBeneficeRepo> {

    public VBeneficeService(VBeneficeRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<VBenefice> getEntityClass() {
        return VBenefice.class;
    }

    public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

    @Override
    public int getPageSize() {
        return 12;
    }

}
