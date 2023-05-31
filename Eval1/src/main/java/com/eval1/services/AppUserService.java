package com.eval1.services;

import com.eval1.models.Role;
import com.eval1.repositories.AppUserRepo;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import java.lang.String;
import java.lang.Integer;
import com.eval1.models.AppUser;


@Service
public class AppUserService extends CrudServiceDeleted<AppUser, AppUserRepo> {

    public AppUserService(AppUserRepo repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public Class<AppUser> getEntityClass() {
        return AppUser.class;
    }

public Integer getRequiredPages (Long count) {
        return (int) Math.ceil((double)count / (double)getPageSize());
    }

    @Override
    public AppUser create(AppUser obj) throws Exception {
        obj.setPassword("1234");
        Role role = new Role();
        role.setId(0L);
        obj.setRole(role);
        return super.create(obj);
    }

    @Override
    public AppUser update(AppUser obj) throws Exception {
        obj.setPassword("1234");
        Role role = new Role();
        role.setId(0L);
        obj.setRole(role);
        return super.update(obj);
    }
}
