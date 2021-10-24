package com.avondock.app.service.carpark.cqrs.query.repository;

import com.avondock.app.service.carpark.cqrs.coreapi.CarParkId;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarParkViewRepository extends JpaRepository<CarParkView, CarParkId>, RevisionRepository<CarParkView, CarParkId, Long> {
    Iterable<CarParkView> findByState(String state);
}

