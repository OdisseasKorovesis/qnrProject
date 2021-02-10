package com.odkor.myQnrProject.services;

import com.odkor.myQnrProject.models.Department;

import java.security.acl.Owner;
import java.util.List;

public interface DepartmentService {

    List<Department> findAll();
    Department findByLocation(Long locationId);
    Department findById(Long id);
    List<Department> findAllByLocation(Long locationId);



}
