package com.avondock.app.service.carpark.gateway;


import com.avondock.app.service.carpark.cqrs.coreapi.AddCarPark;
import com.avondock.app.service.carpark.cqrs.coreapi.ChangeCarPark;
import com.avondock.app.service.carpark.cqrs.query.response.CarParkResponse;
import com.avondock.app.service.carpark.infrastucture.assembler.CarParkAssembler;
import com.avondock.core.shared.gateway.CommandEndpoint;
import com.avondock.app.service.carpark.cqrs.coreapi.CarParkId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@Profile("rest")
@RestController
@RequestMapping("api/v1/carparks")
public class CarParkCommandEndpoint extends CommandEndpoint {


    public CarParkCommandEndpoint(CommandGateway commandGateway, CarParkAssembler assembler) {
        super(commandGateway, assembler);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AddCarPark command) {
        CarParkId carParkId = new CarParkId();
        return sendCreate(command, carParkId.getIdentity(), new CarParkResponse());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ChangeCarPark command) {
        return sendUpdate(command, id, new CarParkResponse());
    }
}
