package com.odkor.myQnrProject.services;

import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll().forEach(departments::add);
        return departments;
    }

    @Override
    public Department findByLocation(Long locationId) {
        Department department = departmentRepository.findByLocation(locationId);
        return department;
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> findAllByLocation(Long locationId) {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAllByLocation(locationId).forEach(departments::add);
        return departments;
    }
}
