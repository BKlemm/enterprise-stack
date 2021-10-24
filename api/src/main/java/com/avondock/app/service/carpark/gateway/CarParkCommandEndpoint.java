package com.avondock.app.service.carpark.gateway;


import com.avondock.core.shared.gateway.CommandEndpoint;
import com.avondock.app.service.carpark.cqrs.coreapi.CarParkDTO;
import com.avondock.app.service.carpark.cqrs.coreapi.CarParkId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@Profile("rest")
@RestController
@RequestMapping("/carparks")
public class CarParkCommandEndpoint extends CommandEndpoint {

    public CarParkCommandEndpoint(CommandGateway commandGateway) {
        super(commandGateway);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CarParkDTO request) {
        CarParkId carParkId = new CarParkId();
        return sendCreate(request, carParkId.getCarParkId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody CarParkDTO request) {
        return sendUpdate(request, id);
    }
}
