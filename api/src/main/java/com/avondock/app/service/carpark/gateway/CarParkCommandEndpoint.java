package com.avondock.app.service.carpark.gateway;


import com.avondock.app.service.carpark.cqrs.coreapi.*;
import com.avondock.core.shared.gateway.CommandEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

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
    public ResponseEntity<?> create(@Valid @RequestBody AddCarParkDTO request) throws ExecutionException, InterruptedException {
        CarParkId carParkId = new CarParkId();
        AddCarPark command = new AddCarPark(
                carParkId,
                request.getIataCode(),
                request.getName(),
                request.getDescription(),
                request.getAddress(),
                request.getSupportEmail(),
                request.getSupportPhone(),
                request.getTax(),
                request.getState()
        );
        return sendCreate(command, request);
    }

    @PutMapping("/{id}")
    @ApiOperation("Change a carparks in the system")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody ChangeCarParkDTO request) {
        ChangeCarPark command = new ChangeCarPark(
                new CarParkId(id),
                request.getIataCode(),
                request.getName(),
                request.getDescription(),
                request.getAddress(),
                request.getSupportEmail(),
                request.getSupportPhone(),
                request.getTax(),
                request.getState()
        );
        return sendUpdate(command, request);
    }
}
