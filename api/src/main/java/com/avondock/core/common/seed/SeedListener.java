package com.avondock.core.common.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SeedListener {

    CarParkSeed carParkSeed;

    @Autowired
    public SeedListener(CarParkSeed carParkSeed) {
        this.carParkSeed = carParkSeed;
    }


    @EventListener
    public void seed(ContextRefreshedEvent event) {
        carParkSeed.seed();
    }
}
