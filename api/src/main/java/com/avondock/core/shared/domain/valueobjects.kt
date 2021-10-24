package com.avondock.core.shared.domain

import com.fasterxml.jackson.annotation.JsonGetter
import lombok.Value
import javax.persistence.Embeddable

enum class PaymentMethod {
    PAYPAL, SEPA, CREDIT_CARD, NONE, MANUAL, EXTERNAL
}

@Value
@Embeddable
data class Currency(
    @get:JsonGetter val code: String = "EUR",
    @get:JsonGetter val symbol: String = "€"
)

data class GuestUUID(
    @get:JsonGetter val id: String = "00000000-0000-0000-0000-000000000000"
)
