package com.avondock.app.service.carpark.cqrs.coreapi

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

abstract class AbstractCarParkDTO(
    @NotNull
    @Size(min = 3, max = 3, message = "Iata Code must be a length of 3")
    @JsonProperty("iataCode")
    open val iataCode: String,
    @NotNull
    @Size(min = 3, message = "Name has to be a min length of 3")
    @JsonProperty("name")
    open val name: String,
    @JsonProperty("description")
    open val description: String?,
    @JsonProperty("address")
    open val address: CarParkAddress,
    @NotNull
    @Email(message = "Support Email should be valid")
    @JsonProperty("supportEmail")
    open val supportEmail: String,
    @NotNull(message = "Support Phone cannot be null")
    @JsonProperty("supportPhone")
    open val supportPhone: String,
    @NotNull(message = "Tax cannot be null")
    @Positive(message = "Tax must be positiv")
    @JsonProperty("tax")
    open val tax: BigDecimal,
    @NotNull(message = "State cannot be null")
    @JsonProperty("state")
    open val state: CarParkStatus
): RepresentationModel<AbstractCarParkDTO>()

class AddCarParkDTO(
    override val iataCode: String,
    override val name: String,
    override val description: String?,
    override val address: CarParkAddress,
    override val supportEmail: String,
    override val supportPhone: String,
    override val tax: BigDecimal,
    override val state: CarParkStatus
): AbstractCarParkDTO(iataCode, name, description, address, supportEmail, supportPhone, tax, state)

class ChangeCarParkDTO(
    @NotNull(message = "CarParkId cannot be null")
    @JsonProperty("carParkId")
    val id: CarParkId,
    override val iataCode: String,
    override val name: String,
    override val description: String?,
    override val address: CarParkAddress,
    override val supportEmail: String,
    override val supportPhone: String,
    override val tax: BigDecimal,
    override val state: CarParkStatus
): AbstractCarParkDTO(iataCode, name, description, address, supportEmail, supportPhone, tax, state)
