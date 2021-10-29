package com.avondock.app.service.carpark.cqrs.coreapi

import org.axonframework.serialization.Revision
import java.math.BigDecimal

abstract class CarParkEvent(open val carParkId: CarParkId)

abstract class AbstractCarParkEvent(
    override val carParkId: CarParkId,
    open val address: CarParkAddress,
    open val name: String,
    open val iataCode: String,
    open val supportEmail: String,
    open val supportPhone: String,
    open val tax: BigDecimal,
    open val description: String?
): CarParkEvent(carParkId)

@Revision("1") //that's your event revision, if some parts changed on future, you have to upcast your event
data class CarParkAdded(
    override val carParkId: CarParkId,
    override val address: CarParkAddress,
    override val name: String,
    override val iataCode: String,
    override val supportEmail: String,
    override val supportPhone: String,
    override val tax: BigDecimal,
    override val description: String?
) : AbstractCarParkEvent(carParkId, address, name, iataCode, supportEmail, supportPhone, tax, description)

data class CarParkChanged(
    override val carParkId: CarParkId,
    override val address: CarParkAddress,
    override val name: String,
    override val iataCode: String,
    override val supportEmail: String,
    override val supportPhone: String,
    override val tax: BigDecimal,
    override val description: String?,
    val state: String
) : AbstractCarParkEvent(carParkId, address, name, iataCode, supportEmail, supportPhone, tax, description)

// This Events are only examples they belongs normally to another context/service
data class Example2Event(
    override val carParkId: CarParkId,
): CarParkEvent(carParkId)

data class ExampleEvent(
    val paymentId: String,
)
data class Example3Event(
    val invoiceId: String,
)
