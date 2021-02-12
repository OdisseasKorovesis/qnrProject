package com.odkor.myQnrProject.repositories;

import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    DepartmentRepository departmentRepository;

    Department department;
    List<Department> departments;
    Location location;

    @BeforeEach
    void setUp() {

        location = new Location();
        location.setName("mockLocation");


        departments = new ArrayList<>();
        department = new Department();
        department.setLocation(location);
        departments.add(department);
    }

    @Test
    void findAllByLocation() {
        Long id = testEntityManager.persistAndGetId(location, Long.class);
        testEntityManager.persist(department);
        List<Department> foundDepartments = departmentRepository.findAllByLocation(id);
        assertEquals(departments, foundDepartments);
    }
}