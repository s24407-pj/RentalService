package com.example.rentalservice.controller;

import com.example.rentalservice.model.Movie;
import com.example.rentalservice.service.RentalService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(("/rental"))
public class RentalRestController {
    private final RentalService rentalService;


    public RentalRestController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable ObjectId id) {
        return ResponseEntity.ok(rentalService.getMovieById(id));
    }

    @PatchMapping("/{id}/availability/true")
    public ResponseEntity returnMovie(@PathVariable ObjectId id) {
        return ResponseEntity.ok(rentalService.returnMovie(id));
    }

    @PatchMapping("/{id}/availability/false")
    public ResponseEntity rentMovie(@PathVariable ObjectId id) {
        return ResponseEntity.ok(rentalService.rentMovie(id));
    }

}
