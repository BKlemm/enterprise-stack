package com.avondock.app.service.carpark.cqrs.coreapi

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.RepresentationModel
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import javax.validation.constraints.*

abstract class AbstractCarParkDTO(
    @field:Size(min = 3, max = 3, message = "Iata Code must be a length of 3")
    @JsonProperty("iataCode", required = true)
    open val iataCode: String,
    @field:Size(min = 3, message = "Name has to be a min length of 3")
    @JsonProperty("name", required = true)
    open val name: String,
    @JsonProperty("description")
    open val description: String?,
    @JsonProperty("description", required = true)
    open val address: CarParkAddressDTO,
    @field:NotBlank
    @field:Email(message = "Support Email should be valid")
    @JsonProperty("supportEmail")
    open val supportEmail: String,
    @field:NotBlank(message = "Support Phone cannot be null")
    @JsonProperty("supportPhone")
    open val supportPhone: String,
    @JsonProperty("tax", required = true)
    open val tax: BigDecimal,
    @JsonProperty("state", required = true)
    open val state: CarParkStatus
): RepresentationModel<AbstractCarParkDTO>()


data class AddCarParkDTO(
    override val iataCode: String,
    override val name: String,
    override val description: String?,
    override val address: CarParkAddressDTO,
    override val supportEmail: String,
    override val supportPhone: String,
    override val tax: BigDecimal,
    override val state: CarParkStatus
): AbstractCarParkDTO(iataCode, name, description, address, supportEmail, supportPhone, tax, state)

class ChangeCarParkDTO(
    @JsonProperty("carParkId", required = true)
    val id: CarParkId,
    override val iataCode: String,
    override val name: String,
    override val description: String?,
    override val address: CarParkAddressDTO,
    override val supportEmail: String,
    override val supportPhone: String,
    override val tax: BigDecimal,
    override val state: CarParkStatus
): AbstractCarParkDTO(iataCode, name, description, address, supportEmail, supportPhone, tax, state)


class CarParkAddressDTO (
    val street: String,
    val number: String,
    val city: String,
    val zip: String,
    val country: String,
    val region: String?,
    val latitude: String,
    val longitude: String
)
