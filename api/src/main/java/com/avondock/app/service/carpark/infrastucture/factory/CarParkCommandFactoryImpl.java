package com.avondock.app.service.carpark.infrastucture.factory;

import com.avondock.app.service.carpark.cqrs.command.factory.CarParkCommandFactory;
import com.avondock.app.service.carpark.cqrs.coreapi.*;
import com.avondock.core.shared.infrastructure.factory.CommandFactoryImpl;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

public class CarParkCommandFactoryImpl extends CommandFactoryImpl implements CarParkCommandFactory {

    public AbstractCarParkCommand create(AbstractCarParkDTO request, Class<?> command, CarParkId id) {
        this.construct(command,
            CarParkId.class, String.class, String.class, String.class,
            CarParkAddress.class, String.class, String.class, BigDecimal.class, CarParkStatus.class
        );

        ModelMapper mapper = new ModelMapper();
        CarParkAddress address = mapper.map(request.getAddress(), CarParkAddress.class);

        return (AbstractCarParkCommand) this.createInstance(
            id,
            request.getIataCode(),
            request.getName(),
            request.getDescription(),
            address,
            request.getSupportEmail(),
            request.getSupportPhone(),
            request.getTax(),
            request.getState()
        );
    }
}
