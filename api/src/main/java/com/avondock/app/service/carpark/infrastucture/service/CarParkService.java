package com.avondock.app.service.carpark.infrastucture.service;

import com.avondock.app.service.carpark.cqrs.coreapi.CarParkId;
import com.avondock.app.service.carpark.cqrs.coreapi.CarParkStatus;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import com.avondock.app.service.carpark.cqrs.query.repository.CarParkViewRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CarParkService {

    private final CarParkViewRepository repository;

    @Autowired
    public CarParkService(CarParkViewRepository repository) {
        this.repository = repository;
    }

    public void save(CarParkView carParkView) {
        repository.save(carParkView);
    }

    public CarParkView carparkById(CarParkId id) {
        return repository.findById(id).orElseThrow();
    }

    public CarParkView carparkById(String id) {
        return repository.findById(new CarParkId(id)).orElseThrow();
    }

    public Iterable<CarParkView> carparksByState(CarParkStatus state) {
        return repository.findByState(state.name());
    }

    public Iterable<CarParkView> carparks() {
        return repository.findAll();
    }

    public Page<CarParkView> carparks(PageRequest request) {
        return repository.findAll(request);
    }

}
