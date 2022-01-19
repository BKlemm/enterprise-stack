package com.avondock.app.service.carpark.infrastucture.service;

import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkId;
import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkStatus;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import com.avondock.app.service.carpark.cqrs.query.repository.CarParkViewRepository;
import com.avondock.core.common.http.HttpFilter;
import com.avondock.core.shared.infrastructure.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CarParkService extends BaseService<CarParkView, CarParkId, CarParkViewRepository> {

    @Autowired
    public CarParkService(CarParkViewRepository repository) {
        super(repository);
    }

    public void addCarpark(CarParkView carParkView) {
        this.save(carParkView);
    }

    public void changeCarpark(CarParkView carParkView) {
        this.update(carParkView);
    }

    public CarParkView carparkById(CarParkId id) {
        return repository.findById(id).orElseThrow();
    }

    public CarParkView carparkById(String id) {
        return repository.findById(new CarParkId(id)).orElseThrow();
    }

    public Iterable<CarParkView> carparks() {
        return repository.findAll();
    }

    public Iterable<CarParkView> carparksByState(CarParkStatus state) {
        return repository.findByCarParkStatus(state);
    }

    public Page<CarParkView> carparks(Pageable request, HttpFilter filter) {
        if (filter.isFilterable()) {
            return new PageImpl<>(this.filter(CarParkView.class, filter.getFilter()));
        }
        return repository.findAll(request);
    }

}
