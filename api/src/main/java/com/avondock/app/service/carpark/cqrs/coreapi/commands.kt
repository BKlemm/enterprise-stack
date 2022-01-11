package com.avondock.app.service.carpark.cqrs.coreapi;

import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkAddress
import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkId
import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkStatus
import com.avondock.core.shared.gateway.contracts.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal

abstract class CarParkCommand(@TargetAggregateIdentifier open val carParkId: CarParkId): Command<CarParkId> {
    override fun getIdentity(): CarParkId {
        return carParkId
    }
}

class AddCarPark(
    override val carParkId: CarParkId,
    val iataCode: String,
    val name: String,
    val description: String?,
    val address: CarParkAddress,
    val supportEmail: String,
    val supportPhone: String,
    val tax: BigDecimal,
    val state: CarParkStatus,
    val lotAmount: Int
) : CarParkCommand(carParkId)

class ChangeCarPark(
    override val carParkId: CarParkId,
    val iataCode: String,
    val name: String,
    val description: String?,
    val address: CarParkAddress,
    val supportEmail: String,
    val supportPhone: String,
    val tax: BigDecimal,
    val state: CarParkStatus
) : CarParkCommand(carParkId)
