package com.odkor.myQnrProject.controllers;

import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.services.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping({"/locations"})
    public String showLocations(Model model) {

        log.info("Attempting to find all locations...");

        List<Location> locations = locationService.findAll();
        if(locations.isEmpty()) {
            log.info("Attempt was successful, displaying results.");
            model.addAttribute("locations", locationService.findAll());
            return "location/allLocations";
        }

        log.info("Attempt did not return any results, displaying error page.");
        model.addAttribute("notFound", "locations");
        return "error/notFound";

    }
}
