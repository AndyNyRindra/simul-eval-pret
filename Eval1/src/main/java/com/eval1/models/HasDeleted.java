package com.eval1.models;

import custom.springutils.model.HasId;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class HasDeleted extends HasId {

    Boolean deleted = false;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
