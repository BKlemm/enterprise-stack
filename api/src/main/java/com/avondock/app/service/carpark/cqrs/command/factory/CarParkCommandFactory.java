package com.avondock.app.service.carpark.cqrs.command.factory;

import com.avondock.app.service.carpark.cqrs.coreapi.AbstractCarParkDTO;
import com.avondock.app.service.carpark.cqrs.coreapi.CarParkCommand;
import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkId;
import org.springframework.stereotype.Component;

@Component
public interface CarParkCommandFactory {
    CarParkCommand create(AbstractCarParkDTO request, Class<?> command, CarParkId id);
}
