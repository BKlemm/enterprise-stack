package com.avondock.app.service.carpark.cqrs.coreapi;

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import lombok.NoArgsConstructor
import lombok.Value
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

enum class CarParkStatus { ACTIVE, INACTIVE, FULL }

@Value
@Embeddable
@NoArgsConstructor
data class CarParkAddress(
    val street: String = "",
    val number: String = "",
    val city: String = "",
    val zip: String = "",
    val country: String = "",
    val region: String? = null,
    val latitude: String = "",
    val longitude: String = ""
)

@Value
@Embeddable
@NoArgsConstructor
data class CarParkId @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(
    @get:JsonValue val carParkId: String = UUID.randomUUID().toString()
): Serializable


