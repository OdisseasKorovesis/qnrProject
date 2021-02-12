package com.odkor.myQnrProject.controllers.restControllers;

import com.odkor.myQnrProject.controllers.nonRestControllers.LocationController;
import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.services.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LocationControllerRestTest {

    @Mock
    LocationService locationService;

    @InjectMocks
    LocationControllerRest locationController;

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


        String EXPECTED = "<List>" +
                "<item>" +
                "<id>1</id>" +
                "<name></name>" +
                "</item>" +
                "</List>";
        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(content().xml(EXPECTED));
    }

    @Test
    void showLocations_Success_EmptyList() throws Exception {
        when(locationService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/locations"))
                .andExpect(status().isNoContent());
    }

    @Test
    void showLocations_Error() throws Exception {
        when(locationService.findAll()).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/locations"))
                .andExpect(status().isInternalServerError());
    }
}