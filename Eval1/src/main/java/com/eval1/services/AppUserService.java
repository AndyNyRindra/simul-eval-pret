package com.eval1.services;

import com.eval1.models.Role;
import com.eval1.models.appUser.AppUserFilter;
import com.eval1.repositories.AppUserRepo;
import custom.springutils.search.Condition;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.lang.Integer;
import java.util.List;

import com.eval1.models.appUser.AppUser;


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

    @Override
    public List<Condition> getAdditionalConditionFrom(Object filter) throws Exception {
        AppUserFilter appUserFilter = (AppUserFilter) filter;
        List<Condition> list = super.getAdditionalConditionFrom(appUserFilter);
        Condition condition = new Condition();
        condition.setCondition(" and role.id = 0");
        list.add(condition);
        if (appUserFilter.getKeyWord() != null) {
            Condition condition2 = new Condition();
            condition2.setCondition(" and (name ilike '%" + appUserFilter.getKeyWord() + "%' or email ilike '%" + appUserFilter.getKeyWord() + "%') ");
            list.add(condition2);
        }
        return list;
    }
}
