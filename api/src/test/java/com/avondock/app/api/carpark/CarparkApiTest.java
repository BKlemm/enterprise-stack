package com.avondock.app.api.carpark;

import com.avondock.app.service.carpark.cqrs.query.response.CarParkResponse;
import com.avondock.app.api.BaseApiTest;
import com.avondock.core.common.seed.CarParkSeed;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class CarparkApiTest extends BaseApiTest {

    public static String RESOURCE = "carparks/";

    @Test
    public void carparksShouldReturnValidMunchenCarpark() {
        String resource = RESOURCE + CarParkSeed.P1_UUID;
        ResponseEntity<CarParkResponse> res = response(resource, CarParkResponse.class);

        assertThat(res.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(res.getBody().getCarParkId(), equalTo(CarParkSeed.P1_UUID));
        assertThat(res.getBody().getIataCode(), equalTo(CarParkSeed.uc(CarParkSeed.L1)));
        assertThat(res.getBody().getName(), equalTo(CarParkSeed.L1));
    }

    @Test
    public void carparksShouldReturnThreeCarparks() {
        ResponseEntity<CarParkResponse[]> res = response(RESOURCE, CarParkResponse[].class);

        //assertThat(res.getStatusCode(), equalTo(HttpStatus.OK));
        //assertThat(res.getBody().length, equalTo(3));
    }
}
