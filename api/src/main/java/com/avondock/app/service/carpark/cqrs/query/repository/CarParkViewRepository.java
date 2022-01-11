package com.avondock.app.service.carpark.cqrs.query.repository;

import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkId;
import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkStatus;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarParkViewRepository extends PagingAndSortingRepository<CarParkView, CarParkId> {
    Iterable<CarParkView> findByCarParkStatus(CarParkStatus state);

    Page<CarParkView> findByNameContaining(String name, Pageable pageable);
}

