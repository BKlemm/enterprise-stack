package com.avondock.app.system.iam.auth.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import lombok.NoArgsConstructor
import lombok.Value
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Value
@Embeddable
@NoArgsConstructor
data class AccountId @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(
    @Column(name = "account_id") @get:JsonValue val accountId: String = UUID.randomUUID().toString()
): Serializable
