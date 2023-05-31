package com.eval1.services;

import com.eval1.models.HasDeleted;
import custom.springutils.search.Condition;
import custom.springutils.service.CrudService;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class CrudServiceDeleted <E extends HasDeleted, R extends JpaRepository<E, Long>> extends CrudService<E, R> {

    public CrudServiceDeleted(R repo, EntityManager manager) {
        super(repo, manager);
    }

    @Override
    public void delete(Long id) throws Exception {
        E entity = repo.findById(id).orElseThrow(() -> new Exception("Entity not found"));
        entity.setDeleted(true);
        repo.save(entity);
    }

    @Override
    public List<Condition> getAdditionalConditionFrom(Object filter) throws Exception {
        List<Condition> list = super.getAdditionalConditionFrom(filter);
        Condition condition = new Condition();
        condition.setCondition(" and deleted = false");
        list.add(condition);
        return list;
    }
}
