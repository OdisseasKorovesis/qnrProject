package com.odkor.myQnrProject.services;

import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.repositories.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationServiceImpl locationService;

    Location location;
    List<Location> locations;

    @BeforeEach
    void setUp() {
        location = new Location();
        location.setId(1L);
        locations = new ArrayList<>();
        locations.add(location);
    }

    @Test
    void findById() {
        when(locationRepository.findById(anyLong())).thenReturn(Optional.ofNullable(location));
        Location foundLocation = locationService.findById(1L);
        assertEquals(location, foundLocation);
    }

    @Test
    void findAll() {
        when(locationRepository.findAll()).thenReturn(locations);
        List<Location> foundLocations = locationService.findAll();
        assertEquals(locations, foundLocations);

    }
}