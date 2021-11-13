package com.avondock.app.service.carpark.gateway;


import com.avondock.app.service.carpark.cqrs.coreapi.*;
import com.avondock.app.service.carpark.infrastucture.factory.CarParkCommandFactory;
import com.avondock.core.shared.gateway.CommandEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@Profile("rest")
@RestController
@RequestMapping("api/v1/carparks")
@Api(consumes = "Set of WRITE endpoints to create and change carparks")
public class CarParkCommandEndpoint extends CommandEndpoint {

    public CarParkCommandEndpoint(CommandGateway commandGateway) {
        super(commandGateway);
    }

    @PostMapping
    @ApiOperation("Add a carpark in the system")
    public ResponseEntity<?> create(@Valid @RequestBody AddCarParkDTO request) {
        AddCarPark command = (AddCarPark) CarParkCommandFactory.create(request, AddCarPark.class, new CarParkId());
        return sendCreate(command, request);
    }

    @PutMapping("/{id}")
    @ApiOperation("Change a carparks in the system")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody ChangeCarParkDTO request) {
        ChangeCarPark command = (ChangeCarPark) CarParkCommandFactory.create(request, ChangeCarPark.class, new CarParkId(id));
        return sendUpdate(command, request);
    }
}
