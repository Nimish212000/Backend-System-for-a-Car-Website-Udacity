package org.udacity.vehicles.api;


import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.udacity.vehicles.domain.car.Car;
import org.udacity.vehicles.service.CarService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Implements a REST-based controller for the Vehicles API.
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarResourceAssembler assembler;

    CarController(CarService carService, CarResourceAssembler assembler) {
        this.carService = carService;
        this.assembler = assembler;
    }

    /**
     * Creates a list to store any vehicles.
     * @return list of vehicles
     */
    @GetMapping
    public ResponseEntity<List<EntityModel<Car>>> list() {
        List<EntityModel<Car>> resources = carService.list().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    /**
     * Gets information of a specific car by D.
     * @param id the id number of the given vehicle
     * @return all information for the requested vehicle
     */
    @GetMapping("/{id}")
    public EntityModel<Car> get(@PathVariable Long id) {
        /**
         * TODO: Use the 'findById' method from the Car Service to get car information.
         * TODO: Use the 'assembler' on that car and return the resulting output.
         *       Update the first line as part of the above implementing.
         */
        Car car = carService.findById(id);
        return assembler.toModel(car);
    }

    /**
     * Posts information to create a new vehicle in the system.
     * @param car A new vehicle to add to the system.
     * @return response that the new vehicle was added to the system
     * @throws URISyntaxException if the request contains invalid fields or syntax
     */
    @PostMapping
    public ResponseEntity<EntityModel<Car>> post(@Valid @RequestBody Car car) throws URISyntaxException {
        /**
         * TODO: Use the 'save' method from the Car Service to save the input car.
         * TODO: Use the 'assembler' on that saved car and return as part of the response.
         *       Update the first line as part of the above implementing.
         */
        Car savedCar = carService.save(car);
        EntityModel<Car> resource = assembler.toModel(savedCar);
        URI carUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCar.getId())
                .toUri();
        return ResponseEntity.created(carUri).body(resource);
    }

    /**
     * Updates the information of a vehicle in the system.
     * @param id The ID number for which to update vehicle information.
     * @param car The updated information about the related vehicle.
     * @return response that the vehicle was updated in the system
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Car>> put(@PathVariable Long id, @Valid @RequestBody Car car) {
        /**
         * TODO: Set the id of the input car object to the `id` input.
         * TODO: Save the car using the 'save' method from the Car service
         * TODO: Use the 'assembler' on that updated car and return as part of the response.
         *       Update the first line as part of the above implementing.
         */
        car.setId(id);
        return ResponseEntity.ok(assembler.toModel(carService.save(car)));
    }

    /**
     * Removes a vehicle from the system.
     * @param id The ID number of the vehicle to remove.
     * @return response that the related vehicle is no longer in the system
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        /**
         * TODO: Use the Car Service to delete the requested vehicle.
         */
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}