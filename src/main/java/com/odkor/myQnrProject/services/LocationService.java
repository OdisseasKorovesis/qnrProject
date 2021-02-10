package com.odkor.myQnrProject.services;

import com.odkor.myQnrProject.models.Location;

import java.util.List;

public interface LocationService {

    Location findById(Long id);
    List<Location> findAll();
}
