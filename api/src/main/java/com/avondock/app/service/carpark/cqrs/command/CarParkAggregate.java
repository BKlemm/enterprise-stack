package com.avondock.app.service.carpark.cqrs.command;

import com.avondock.app.service.carpark.cqrs.coreapi.*;
import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkAddress;
import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkId;
import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkStatus;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor
// Uncomment to activate profile
//@Profile("command")
// Uncomment to specify a specific processing group for your command models
//@ProcessingGroup("commandModel")
public class CarParkAggregate {

    @AggregateIdentifier
    CarParkId carParkId;

    String iataCode;
    String name;
    String description;
    String supportEmail;
    String supportPhone;

    CarParkAddress address;
    CarParkStatus  carParkStatus;

    BigDecimal tax;

    @CommandHandler
    public CarParkAggregate(AddCarPark command) {
        log.debug("CREATING {}", command);
        apply(new CarParkAdded(
                command.getCarParkId(),
                command.getAddress(),
                command.getName(),
                command.getIataCode(),
                command.getSupportEmail(),
                command.getSupportPhone(),
                command.getTax(),
                command.getDescription(),
                command.getState()
        ));
    }

    @CommandHandler
    public void handle(ChangeCarPark c) {
        log.debug("CHANGING {}", c);
        apply(new CarParkChanged(
                c.getCarParkId(),
                c.getAddress(),
                c.getName(),
                c.getIataCode(),
                c.getSupportEmail(),
                c.getSupportPhone(),
                c.getTax(),
                c.getDescription(),
                c.getState()
        ));
    }


    @EventSourcingHandler
    public void on(CarParkAdded e) {
        log.debug("APPLYING {}", e);
        this.initCarparkEvent(e);
    }

    @EventSourcingHandler
    public void on(CarParkChanged e) {
        log.debug("APPLYING {}", e);
        this.initCarparkEvent(e);
    }

    private void initCarparkEvent(AbstractCarParkEvent e) {
        this.carParkId = e.getCarParkId();
        this.name = e.getName();
        this.description = e.getDescription();
        this.iataCode = e.getIataCode();
        this.address = e.getAddress();
        this.supportEmail = e.getSupportEmail();
        this.supportPhone = e.getSupportPhone();
        this.tax = e.getTax();
        this.carParkStatus = e.getState();
    }

}
