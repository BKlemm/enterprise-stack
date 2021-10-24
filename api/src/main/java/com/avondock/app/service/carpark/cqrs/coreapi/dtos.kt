package com.avondock.app.service.carpark.cqrs.coreapi

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.RepresentationModel
import java.math.BigDecimal
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class CarParkDTO(
    @JsonProperty("carParkId") val id: CarParkId,
    @NotNull @Size(min = 3, max = 3) @JsonProperty("iataCode") val iataCode: String,
    @NotNull @JsonProperty("name") val name: String,
    @NotNull @JsonProperty("description") val description: String?,
    @NotNull @JsonProperty("address") val address: CarParkAddress,
    @NotNull @JsonProperty("supportEmail") val supportEmail: String,
    @NotNull @JsonProperty("supportPhone") val supportPhone: String,
    @NotNull @JsonProperty("tax") val tax: BigDecimal,
): RepresentationModel<CarParkDTO>()
