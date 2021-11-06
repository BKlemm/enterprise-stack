package com.avondock.core.common.seed;

import com.github.javafaker.Faker;
import com.avondock.app.service.carpark.cqrs.coreapi.CarParkAddress;
import com.avondock.app.service.carpark.cqrs.coreapi.CarParkId;
import com.avondock.app.service.carpark.cqrs.coreapi.CarParkStatus;
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView;
import com.avondock.app.service.carpark.cqrs.query.repository.CarParkViewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class CarParkSeed {

    public static final String P1_UUID = "1a333866-79d9-4689-8d03-53dbe4f91b81";
    public static final String P2_UUID = "104fece5-1c80-41cb-a083-95ac638ab705";
    public static final String P3_UUID = "b2abb642-dab1-4b07-a460-d0deac519cac";
    public static final String L1 = "Munchen";
    public static final String L2 = "Frankfurt";
    public static final String L3 = "Hamburg";

    private final CarParkViewRepository carParkViewRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarParkSeed(CarParkViewRepository carParkViewRepository, JdbcTemplate jdbcTemplate) {
        this.carParkViewRepository = carParkViewRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void seed() {
        seedCarPark(P1_UUID, L1);
        seedCarPark(P2_UUID, L2);
        seedCarPark(P3_UUID, L3);
    }

    private void seedCarPark(String uuid, String location) {
        String            sql = "SELECT * FROM car_park WHERE identity = '" + uuid + "'";
        List<CarParkView> rs  = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (rs.size() <= 0) {
            carParkViewRepository.save(createCarPark(uuid, location));
            log.info("CarPark seeded with uuid: " + uuid);
        }
    }

    private CarParkView createCarPark(String uuid, String location) {
        Faker faker = new Faker();

        return new CarParkView(
                new CarParkId(uuid),
                uc(location),
                location,
                location + " Parking Airport",
                new CarParkAddress(
                        faker.address().streetAddress(),
                        faker.address().streetAddressNumber(),
                        location,
                        faker.address().zipCode(),
                        "Deutschland",
                        null,
                        faker.address().latitude(),
                        faker.address().longitude()
                ),
                faker.internet().safeEmailAddress(),
                faker.phoneNumber().phoneNumber(),
                BigDecimal.valueOf(19.00),
                CarParkStatus.ACTIVE
        );
    }

    public static String uc(String location) {
        return location.substring(0,3).toUpperCase();
    }
}
