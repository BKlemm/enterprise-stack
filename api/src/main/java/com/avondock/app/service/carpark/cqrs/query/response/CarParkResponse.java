package com.avondock.app.service.carpark.cqrs.query.response;

import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkAddress;
import com.avondock.app.service.carpark.cqrs.coreapi.valueobjects.CarParkStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "carpark")
@Relation(collectionRelation = "carparks")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarParkResponse extends RepresentationModel<CarParkResponse> implements Serializable {

    private String         carParkId;
    private String         name;
    private String         description;
    private String         iataCode;
    private String         supportEmail;
    private String         supportPhone;
    private BigDecimal     tax;
    @JsonDeserialize
    private CarParkStatus  carParkStatus;
    private CarParkAddress address;
}
