package com.avondock.app.service.carpark.cqrs.query;


import com.avondock.app.service.carpark.cqrs.coreapi.*;
import com.avondock.app.service.carpark.infrastucture.assembler.CarParkAssembler;
import com.avondock.app.service.carpark.cqrs.query.response.CarParkResponse;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import com.avondock.app.service.carpark.infrastucture.service.CarParkService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
// Uncomment to activate profile
//@Profile("query")
// Uncomment to specify a specific processing group for your query models
//@ProcessingGroup("query")
public class CarParkProjection {

    private final CarParkAssembler assembler;
    private final CarParkService carParkService;

    @Autowired
    public CarParkProjection(CarParkService carParkService, CarParkAssembler assembler) {
        this.carParkService = carParkService;
        this.assembler = assembler;
    }


    @EventHandler
    public void on(CarParkAdded e) {
        log.debug("PROJECTION {}", e);

        CarParkView carpark = new CarParkView(
                e.getCarParkId(),
                e.getIataCode(),
                e.getName(),
                e.getDescription(),
                new CarParkAddress(
                        e.getAddress().getStreet(),
                        e.getAddress().getNumber(),
                        e.getAddress().getCity(),
                        e.getAddress().getZip(),
                        e.getAddress().getCountry(),
                        e.getAddress().getRegion(),
                        e.getAddress().getLatitude(),
                        e.getAddress().getLongitude()
                ),
                e.getSupportEmail(),
                e.getSupportPhone(),
                e.getTax(),
                e.getState()
        );

        carParkService.save(carpark);
    }

    @EventHandler
    public void on(CarParkChanged e) {
        log.debug("PROJECTION {}", e);
        CarParkView carpark = carParkService.carparkById(e.getCarParkId());

        carpark.setAddress(e.getAddress());
        carpark.setSupportEmail(e.getSupportEmail());
        carpark.setName(e.getName());
        carpark.setSupportPhone(e.getSupportPhone());
        carpark.setDescription(e.getDescription());
        carpark.setIataCode(e.getIataCode());
        carpark.setTax(e.getTax());
        carpark.setState(e.getState());

        carParkService.save(carpark);

    }

    @QueryHandler
    public List<CarParkResponse> handle(ListAllCarParks query) {

        Iterable<CarParkView> carParks = carParkService.carparksByState(CarParkStatus.ACTIVE);
        return assembler.toListModel(carParks);
    }

    @QueryHandler
    public List<CarParkResponse> handle(ListCarParks query) {
        Sort sort = query.getSort().equals("asc") ? Sort.by("name").ascending() : Sort.by("name").descending();
        PageRequest request = PageRequest.of(query.getPage(), query.getSize(), sort);
        Iterable<CarParkView> carParks = carParkService.carparks(request);
        return assembler.toListModel(carParks);
    }

    @QueryHandler
    public Iterable<CarParkView> handle(ListCarParksExtend query) {
        return carParkService.carparks();
    }

    @QueryHandler
    public CarParkView handle(GetCarPark query) {
        return carParkService.carparkById(query.getId());
    }
}
