package com.avondock.app.service.carpark.infrastucture.assembler;

import com.avondock.app.service.carpark.cqrs.query.response.CarParkResponse;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import com.avondock.app.service.carpark.gateway.CarParkQueryEndpoint;
import com.avondock.core.common.Assembler;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CarParkAssembler extends RepresentationModelAssemblerSupport<CarParkView, CarParkResponse> implements Assembler {

    public CarParkAssembler() {
        super(CarParkQueryEndpoint.class, CarParkResponse.class);
    }

    @NotNull
    @Override
    public CarParkResponse toModel(@NotNull CarParkView entity) {
        CarParkResponse model = instantiateModel(entity);

        Link selfLink = linkTo(CarParkQueryEndpoint.class).slash(entity.getCarParkId().getIdentity()).withSelfRel();
        Link tariffLink = linkTo(CarParkQueryEndpoint.class).slash(entity.getCarParkId().getIdentity()).slash("tariffs").withRel("tariffs");

        model.setCarParkId(entity.getCarParkId().getIdentity());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setIataCode(entity.getIataCode());
        model.setAddress(entity.getAddress());
        model.setSupportEmail(entity.getSupportEmail());
        model.setSupportPhone(entity.getSupportPhone());
        model.setTax(entity.getTax());
        model.setState(entity.getState());
        model.add(selfLink);
        model.add(tariffLink);

        return model;
    }

    @NotNull
    public List<CarParkResponse> toListModel(@NotNull Iterable<? extends CarParkView> entities) {
        List<CarParkResponse> models = new ArrayList<>();

        for (CarParkView entity: entities) {
            models.add(toModel(entity));
        }

        return models;
    }


    @NotNull
    @Override
    public CollectionModel<CarParkResponse> toCollectionModel(@NotNull Iterable<? extends CarParkView> entities) {
        CollectionModel<CarParkResponse> models = super.toCollectionModel(entities);

        models.add(linkTo(methodOn(CarParkQueryEndpoint.class).listAllCarParks()).withSelfRel());

        return models;
    }
}
