package com.avondock.app.service.carpark.cqrs.command.factory;

import com.avondock.app.service.carpark.cqrs.coreapi.AbstractCarParkCommand;
import com.avondock.app.service.carpark.cqrs.coreapi.AbstractCarParkDTO;
import com.avondock.app.service.carpark.cqrs.coreapi.CarParkId;
import org.springframework.stereotype.Component;

@Component
public interface CarParkCommandFactory {
    AbstractCarParkCommand create(AbstractCarParkDTO request, Class<?> command, CarParkId id);
}
