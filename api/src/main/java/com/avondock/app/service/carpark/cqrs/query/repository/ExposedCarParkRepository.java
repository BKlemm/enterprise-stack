package com.avondock.app.service.carpark.cqrs.query.repository;

import com.avondock.app.service.carpark.cqrs.coreapi.CarParkId;
import com.avondock.app.service.carpark.cqrs.coreapi.CarParkStatus;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "carpark", path = "carparks")
public interface ExposedCarParkRepository extends PagingAndSortingRepository<CarParkView, CarParkId> {
    List<CarParkView> findByCarParkStatus(@Param("state") CarParkStatus state);
}
