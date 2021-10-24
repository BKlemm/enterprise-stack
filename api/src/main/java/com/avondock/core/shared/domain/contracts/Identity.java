package com.avondock.core.shared.domain.contracts;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.MappedSuperclass;
import java.util.UUID;

@ToString
@EqualsAndHashCode
@MappedSuperclass
abstract public class Identity {

    protected String id;

    public Identity() {
        this(UUID.randomUUID().toString());
    }

    public Identity(String id) {
        Assert.notNull(id, "Identifier may not be null");
        this.id = id;
    }


}
