package com.avondock.app.service.carpark.cqrs.coreapi;

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import lombok.NoArgsConstructor
import lombok.Value
import org.hibernate.Hibernate
import java.io.Serializable
import java.util.*
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

@Embeddable
@NoArgsConstructor
class CarParkId @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(
    @get:JsonValue val identity: String = UUID.randomUUID().toString()
): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as CarParkId

        return identity != null && identity == other.identity
    }

    override fun hashCode(): Int = Objects.hash(identity);

    @Override
    override fun toString(): String {
        return identity
    }
}


