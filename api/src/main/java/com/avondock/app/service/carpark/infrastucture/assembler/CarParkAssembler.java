package com.avondock.app.service.carpark.infrastucture.assembler;

import com.avondock.app.service.carpark.cqrs.query.response.CarParkResponse;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import com.avondock.app.service.carpark.gateway.CarParkQueryEndpoint;
import com.avondock.core.shared.infrastructure.assembler.BaseAssembler;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CarParkAssembler extends BaseAssembler<CarParkView, CarParkResponse> {

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

        if (expand.contains("lots")){
            /** set lazy loaded lots from this bounded context here
             *  model.setLots(entity.getLots())
             */
        }

        if (expand.contains("reservations")) {
            /** get all active reservations for this carpark from the reservation domain
             *  client.reservations(expand, entity.getCarParkId()) //expand = reservations.active
             *  Note: client is a WebClientAdapter implement see-> com.avondock.core.common.http
             */
        }

        model.add(selfLink);
        model.add(tariffLink);

        return model;
    }


    @NotNull
    @Override
    public CollectionModel<CarParkResponse> toCollectionModel(@NotNull Iterable<? extends CarParkView> entities) {
        CollectionModel<CarParkResponse> response = super.toCollectionModel(entities);
        response.add(linkTo(methodOn(CarParkQueryEndpoint.class).listCarParks(Optional.empty(), Optional.empty())).withSelfRel());
        return response;
    }
}
