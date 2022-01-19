package com.avondock.app.service.carpark.cqrs.coreapi;

import com.avondock.core.shared.gateway.contracts.Query
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView
import com.avondock.core.common.http.HttpFilter
import lombok.Value
import org.springframework.hateoas.RepresentationModel
import java.util.*

abstract class CarParkResultQuery:RepresentationModel<ListCarParksResult>()

@Value class GetCarPark(val id: String, val expand: Optional<String>) : Query

@Value class ListCarParks(val filter: Optional<String>, val expand: Optional<String>) : Query
@Value class ListCarParksResult(val carParks: List<CarParkView>): CarParkResultQuery()


