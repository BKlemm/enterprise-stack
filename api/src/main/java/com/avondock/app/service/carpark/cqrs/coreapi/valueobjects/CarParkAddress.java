package com.avondock.app.service.carpark.cqrs.coreapi.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarParkAddress {
    String street;
    String number;
    String city;
    String zip;
    String country;
    String region;
    String latitude;
    String longitude;
}
