package com.avondock.app.service.carpark.cqrs.query.repository;

import com.avondock.app.service.carpark.cqrs.coreapi.CarParkId;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarParkViewRepository extends JpaRepository<CarParkView, CarParkId> {
    Iterable<CarParkView> findByState(String state);
}

