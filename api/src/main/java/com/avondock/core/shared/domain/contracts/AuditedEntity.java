package com.avondock.core.shared.domain.contracts;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreRemove;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
abstract public class AuditedEntity<U> {

    @CreatedDate
    protected LocalDateTime created;
    @LastModifiedDate
    protected LocalDateTime lastModified;
    protected LocalDateTime deleted;

    @CreatedBy
    protected U createdBy;
    @LastModifiedBy
    protected U lastModifiedBy;

    @PreRemove
    protected void onRemove() {
        deleted = LocalDateTime.now();
    }
}
