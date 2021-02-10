package com.odkor.myQnrProject.controllers.restControllers;

import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.services.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Profile("rest")
public class LocationControllerRest {

    private final LocationService locationService;

    public LocationControllerRest(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping(value = "/locations", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Location>> showLocations(Model model) {

        log.info("Attempting to find all locations...");
        List<Location> locations = locationService.findAll();

        if(!locations.isEmpty()) {
            log.info("Attempt was successful, displaying results.");
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }
        return null;
    }
}
