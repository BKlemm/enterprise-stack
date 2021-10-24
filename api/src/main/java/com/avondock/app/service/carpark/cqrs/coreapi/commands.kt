package com.avondock.app.service.carpark.cqrs.coreapi;

import com.avondock.core.shared.gateway.contracts.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal

abstract class CarParkCommand(@TargetAggregateIdentifier open val carParkId: CarParkId):
    Command

abstract class AbstractCarParkCommand(
    override val carParkId: CarParkId,
    open val iataCode: String,
    open val name: String,
    open val description: String?,
    open val address: CarParkAddress,
    open val supportEmail: String,
    open val supportPhone: String,
    open val tax: BigDecimal,
): CarParkCommand(carParkId)


class AddCarPark(
    override val carParkId: CarParkId,
    override val iataCode: String,
    override val name: String,
    override val description: String?,
    override val address: CarParkAddress,
    override val supportEmail: String,
    override val supportPhone: String,
    override val tax: BigDecimal,
) : AbstractCarParkCommand(carParkId, iataCode, name, description, address, supportEmail, supportPhone, tax)

class ChangeCarPark(
    override val carParkId: CarParkId,
    override val iataCode: String,
    override val name: String,
    override val description: String?,
    override val address: CarParkAddress,
    override val supportEmail: String,
    override val supportPhone: String,
    override val tax: BigDecimal,
    val state: String,
) : AbstractCarParkCommand(carParkId, iataCode, name, description, address, supportEmail, supportPhone, tax)
