package com.avondock.app.service.carpark.cqrs.coreapi;

import com.avondock.core.shared.gateway.contracts.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

abstract class CarParkCommand(@TargetAggregateIdentifier open val carParkId: CarParkId):
    Command

abstract class AbstractCarParkCommand(
    override val carParkId: CarParkId,
    @NotNull @Size(min = 3, max = 3, message = "Iata Code must be a length of 3")
    open val iataCode: String,
    @NotNull @Size(min = 3, message = "Name has to be a min length of 3")
    open val name: String,
    open val description: String?,
    open val address: CarParkAddress,
    @NotNull @Email(message = "Support Email should be valid")
    open val supportEmail: String,
    @NotNull(message = "Support Phone cannot be null")
    open val supportPhone: String,
    @NotNull(message = "Tax cannot be null") @Positive(message = "Tax must be positiv")
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
