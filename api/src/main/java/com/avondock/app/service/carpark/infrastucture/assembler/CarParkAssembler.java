package com.avondock.app.service.carpark.infrastucture.assembler;

import com.avondock.app.service.carpark.cqrs.query.response.CarParkResponse;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import com.avondock.app.service.carpark.gateway.CarParkQueryEndpoint;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CarParkAssembler extends RepresentationModelAssemblerSupport<CarParkView, CarParkResponse> {

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
        model.setCarParkStatus(entity.getCarParkStatus());
        model.add(selfLink);
        model.add(tariffLink);

        return model;
    }


    @NotNull
    @Override
    public CollectionModel<CarParkResponse> toCollectionModel(@NotNull Iterable<? extends CarParkView> entities) {
        CollectionModel<CarParkResponse> response = super.toCollectionModel(entities);
        response.add(linkTo(methodOn(CarParkQueryEndpoint.class).listCarParks("all", "asc", "", 0, 10)).withSelfRel());
        return response;
    }
}
