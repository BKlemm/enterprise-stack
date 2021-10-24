package com.avondock.app.shared.domain;

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonGetter
import lombok.NoArgsConstructor
import lombok.Value
import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

@Value
@Embeddable
@NoArgsConstructor
data class Price @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(
    @get:JsonGetter val amount: BigDecimal = BigDecimal.ZERO,
    @get:JsonGetter val amountNet: BigDecimal = BigDecimal.ZERO,
    @get:JsonGetter val tax: BigDecimal = BigDecimal.ZERO
): Serializable

@Value
@Embeddable
@NoArgsConstructor
data class Fullname @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(
    @get:JsonGetter val firstName: String = "",
    @get:JsonGetter val lastName: String = "",
): Serializable

@Value
@Embeddable
@NoArgsConstructor
data class Address @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(
    @get:JsonGetter val street: String = "",
    @get:JsonGetter val number: String = "",
    @get:JsonGetter val city: String = "",
    @get:JsonGetter val zip: String = "",
): Serializable

