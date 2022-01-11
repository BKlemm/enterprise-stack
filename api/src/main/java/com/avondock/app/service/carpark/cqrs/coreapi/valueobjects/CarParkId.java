package com.avondock.app.service.carpark.cqrs.coreapi.valueobjects;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@ToString
@Embeddable
public class CarParkId implements Serializable {

    @Column(name = "car_park_id") String carParkId;

    public CarParkId() {
        this(UUID.randomUUID().toString());
    }

    public CarParkId(String uuid) {
        carParkId = uuid;
    }

    public String getIdentity() {
        return carParkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarParkId carParkId1 = (CarParkId) o;
        return Objects.equals(carParkId, carParkId1.carParkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carParkId);
    }
}
