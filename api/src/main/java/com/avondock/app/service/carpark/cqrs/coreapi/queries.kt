package com.avondock.app.service.carpark.cqrs.coreapi;

import com.avondock.core.shared.gateway.contracts.Query
import com.avondock.app.service.carpark.cqrs.query.model.CarParkView
import lombok.Value
import org.springframework.hateoas.RepresentationModel
import java.util.*

abstract class CarParkResultQuery:RepresentationModel<ListCarParksResult>()

@Value class GetCarPark(val id: String) : Query

@Value class ListAllCarParks() : Query
@Value class ListCarParks(val filter: String, val sort: String, val page: Int, val size: Int) :
    Query
@Value class ListCarParksExtend() :
    Query
@Value class ListCarParksResult(val carParks: List<CarParkView>): CarParkResultQuery()


