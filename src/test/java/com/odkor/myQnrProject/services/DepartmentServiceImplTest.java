package com.odkor.myQnrProject.services;

import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.repositories.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentServiceImpl departmentService;

    List<Department> departments;
    Department department;

    @BeforeEach
    void setUp() {
        departments = new ArrayList<>();
        Department department = new Department();
        Location location = new Location();
        location.setId(1L);
        location.setName("mockLocation");
        department.setId(1L);
        department.setLocation(location);
        departments.add(department);
    }

    @Test
    void findAll() {
        when(departmentRepository.findAll()).thenReturn(departments);
        List<Department> foundDepartments = departmentService.findAll();
        assertEquals(departments, foundDepartments);
    }

    @Test
    void findById() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(department));
        Department foundDepartment = departmentService.findById(1L);
        assertEquals(department, foundDepartment);
    }

    @Test
    void findAllByLocation() {
        when(departmentRepository.findAllByLocation(anyLong())).thenReturn(departments);
        List<Department> foundDepartments = departmentService.findAllByLocation(1L);
        assertEquals(departments, foundDepartments);
    }
}