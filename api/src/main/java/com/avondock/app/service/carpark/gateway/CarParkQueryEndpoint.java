package com.avondock.app.service.carpark.gateway;


import com.avondock.app.service.carpark.cqrs.coreapi.ListCarParks;
import com.avondock.core.shared.gateway.QueryEndpoint;
import com.avondock.app.service.carpark.cqrs.coreapi.GetCarPark;
import com.avondock.app.service.carpark.cqrs.coreapi.ListAllCarParks;
import com.avondock.app.service.carpark.cqrs.query.response.CarParkResponse;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//@Profile("rest")
@RestController
@RequestMapping(value = "api/v1/carparks", produces = { MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE })
@Api(consumes = "Set of GET endpoints to retrieving carparks")
public class CarParkQueryEndpoint extends QueryEndpoint {

    @Autowired
    public CarParkQueryEndpoint(QueryGateway queryGateway) {
        super(queryGateway);
    }

    @GetMapping("/all")
    @ApiOperation("Returns list of all carparks in the system")
    public CompletableFuture<ResponseEntity<List<CarParkResponse>>> listAllCarParks() {
        return list(new ListAllCarParks(), CarParkResponse.class);
    }


    @GetMapping
    @ApiOperation("Returns sorted,filtered and pagable list of carparks in the system")
    public CompletableFuture<ResponseEntity<List<CarParkResponse>>> listCarParks(
            @RequestParam(name = "filter", defaultValue = "") String filter,
            @RequestParam(name = "sort", defaultValue = "asc") String sort,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) {
        return list(new ListCarParks(filter, sort, page, size), CarParkResponse.class);
    }

    @GetMapping("/{id}")
    @ApiOperation("Return one carpark")
    public CompletableFuture<ResponseEntity<CarParkView>> getCarPark(@PathVariable String id) throws ExecutionException, InterruptedException {
        return get(new GetCarPark(id), CarParkView.class);
    }
}
