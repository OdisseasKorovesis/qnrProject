package com.odkor.myQnrProject.repositories;

import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.models.Employee;
import com.odkor.myQnrProject.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    Employee employee;
    List<Employee> employees;
    Department department;
    Location location;

    @BeforeEach
    void setUp() {

        location = new Location();

        department = new Department();
        department.setLocation(location);

        employee = new Employee();
        employee.setDepartment(department);
        employee.setFirstName("mockFirstName");
        employee.setLastName("mockLastName");

        employees = new ArrayList<>();
        employees.add(employee);
    }

    @Test
    void findByDepartment() {
        testEntityManager.persist(location);
        Long id = testEntityManager.persistAndGetId(department, Long.class);
        testEntityManager.persist(employee);

        List<Employee> foundEmployees = employeeRepository.findByDepartment(1L);
        assertEquals(employees, foundEmployees);
    }

    @Test
    void findByFirstName() {
        testEntityManager.persist(location);
        testEntityManager.persist(department);
        testEntityManager.persist(employee);
        List<Employee> foundEmployees = employeeRepository.findByFirstName("mock");
        assertEquals(employees, foundEmployees);
    }

    @Test
    void findByLastName() {
        testEntityManager.persist(location);
        testEntityManager.persist(department);
        testEntityManager.persist(employee);
        List<Employee> foundEmployees = employeeRepository.findByLastName("mock");
        assertEquals(employees, foundEmployees);
    }
}