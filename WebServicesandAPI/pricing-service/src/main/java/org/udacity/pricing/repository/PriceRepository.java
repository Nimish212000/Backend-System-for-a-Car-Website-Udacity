package org.udacity.pricing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.udacity.pricing.entity.Price;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {
    Price findByVehicleId(@Param("vehicle_id") Long vehicleId);
}
