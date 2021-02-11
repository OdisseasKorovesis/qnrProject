package com.odkor.myQnrProject.controllers.nonRestControllers;

import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.services.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    LocationService locationService;

    @InjectMocks
    LocationController locationController;

    MockMvc mockMvc;
    List<Location> locations;

    @BeforeEach
    void setUp() {
        locations = new ArrayList<>();
        Location location = new Location();
        location.setId(1L);
        locations.add(location);
        mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();
    }

    @Test
    void showLocations_Success_NotEmptyList() throws Exception {

        when(locationService.findAll()).thenReturn(locations);

        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("locations", hasSize(1)))
                .andExpect(view().name("location/allLocations"));
    }

    @Test
    void showLocations_Success_EmptyList() throws Exception {

        when(locationService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("notFound", "locations"))
                .andExpect(view().name("error/notFound"));
    }
}