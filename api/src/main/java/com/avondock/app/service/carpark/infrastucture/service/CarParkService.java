package com.avondock.app.service.carpark.infrastucture.service;

import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkId;
import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkStatus;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import com.avondock.app.service.carpark.cqrs.query.repository.CarParkViewRepository;
import com.avondock.core.common.http.ElasticRestClient;
import com.avondock.core.shared.infrastructure.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CarParkService extends BaseService<CarParkView> {

    private final        CarParkViewRepository repository;
    private final        ElasticRestClient     elastic;
    private static final String                PATH = "/carparks/_doc/";

    @Autowired
    public CarParkService(CarParkViewRepository repository, ElasticRestClient elastic) {
        this.repository = repository;
        this.elastic = elastic;
    }

    public void addCarpark(CarParkView carParkView) {
        repository.save(carParkView);

        elastic.post(PATH + carParkView.getCarParkId().getIdentity(), carParkView);
    }

    public void changeCarpark(CarParkView carParkView) {
        repository.save(carParkView);
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

    public Page<CarParkView> carparks(Pageable request, String filter) {
        if (!filter.equals(FILTER_ALL)) {
            return new PageImpl<>(this.filter(CarParkView.class, filter));
        }
        return repository.findAll(request);
    }

}
