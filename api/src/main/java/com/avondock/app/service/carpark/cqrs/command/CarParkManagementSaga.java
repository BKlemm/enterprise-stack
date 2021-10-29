package com.avondock.app.service.carpark.cqrs.command;

import com.avondock.app.service.carpark.cqrs.coreapi.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
@Saga
public class CarParkManagementSaga {

    private final transient CommandGateway commandGateway;

    @Autowired
    public CarParkManagementSaga(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "carParkId")
    public void handle(CarParkAdded e) {
        String paymentId = UUID.randomUUID().toString();
        log.info("SAGA INVOKED {}", e);

        SagaLifecycle.associateWith("paymentId", paymentId);

        // invoke here your payment command
        commandGateway.send(null);
    }

    @SagaEventHandler(associationProperty = "paymentId")
    public void handle(ExampleEvent e) { //replace ExampleEvent with a real event ex. PaymentPrepared
        String invoiceId = UUID.randomUUID().toString();
        log.info("SAGA CONTINUED {}", e);

        SagaLifecycle.associateWith("invoiceId", invoiceId);

        //invoke here your invoice command
        commandGateway.send(null);
    }

    @SagaEventHandler(associationProperty = "invoiceId")
    public void handle(Example3Event e) { //replace Example2Event with a real event ex. InvoiceCreated
        log.info("SAGA CONTINUED {}", e);

        SagaLifecycle.associateWith("invoiceId", e.getInvoiceId());

        //invoke your CarParkConfirmed command
        commandGateway.send(null);
    }

    @SagaEventHandler(associationProperty = "carParkId")
    public void handle(Example2Event e) { //replace Example2Event with a real event ex. CarParkConfirmed
        log.info("SAGA END {}", e);

        SagaLifecycle.end();
    }
}
