package com.avondock.core.shared.domain.contracts;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
abstract public class BaseEntity {

    protected LocalDateTime created;
    protected LocalDateTime updated;
    protected LocalDateTime deleted;

    @PrePersist
    protected void onCreated() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    protected  void onUpdated() {
        updated = LocalDateTime.now();
    }

    @PreRemove
    protected void onRemove() {
        deleted = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return this.created;
    }
}
