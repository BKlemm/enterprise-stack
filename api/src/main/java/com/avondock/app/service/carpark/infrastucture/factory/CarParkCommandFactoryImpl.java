package com.avondock.app.service.carpark.infrastucture.factory;

import com.avondock.app.service.carpark.cqrs.command.factory.CarParkCommandFactory;
import com.avondock.app.service.carpark.cqrs.coreapi.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class CarParkCommandFactoryImpl implements CarParkCommandFactory {

    public AbstractCarParkCommand create(AbstractCarParkDTO request, Class<?> command, CarParkId id) {
        Constructor<?> ctor;
        try {
            ctor = command.getDeclaredConstructor(CarParkId.class, String.class, String.class, String.class,
                    CarParkAddress.class, String.class, String.class, BigDecimal.class, CarParkStatus.class);
        } catch (NoSuchMethodException noSuchMethodException) {
            return null;
        }

        try {
            return (AbstractCarParkCommand) ctor.newInstance(
                    id,
                    request.getIataCode(),
                    request.getName(),
                    request.getDescription(),
                    new CarParkAddress(
                            request.getAddress().getStreet(),
                            request.getAddress().getNumber(),
                            request.getAddress().getCity(),
                            request.getAddress().getZip(),
                            request.getAddress().getCountry(),
                            request.getAddress().getRegion(),
                            request.getAddress().getLatitude(),
                            request.getAddress().getLongitude()
                    ),
                    request.getSupportEmail(),
                    request.getSupportPhone(),
                    request.getTax(),
                    request.getState()
            );
        } catch(InvocationTargetException | IllegalAccessException | InstantiationException invocationTargetException) {
            return null;
        }


    }
}
