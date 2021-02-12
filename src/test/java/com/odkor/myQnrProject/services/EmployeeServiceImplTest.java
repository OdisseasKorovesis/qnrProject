package com.odkor.myQnrProject.services;

import com.odkor.myQnrProject.models.Employee;
import com.odkor.myQnrProject.repositories.EmployeeRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    List<Employee> employees;
    Employee employee;

    @BeforeEach
    void setUp() {
        employees = new ArrayList<>();
        employee = new Employee();
        employee.setId(1L);
        employees.add(employee);
    }

    @Test
    void findAll() {
        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> foundEmployees = employeeService.findAll();
        assertEquals(employees, foundEmployees);
    }

    @Test
    void findById() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(employee));
        Employee foundEmployee = employeeService.findById(1L);
        assertEquals(employee, foundEmployee);
    }

    @Test
    void findByDepartment() {
        when(employeeRepository.findByDepartment(anyLong())).thenReturn(employees);
        List<Employee> foundEmployees = employeeService.findByDepartment(1L);
        assertEquals(employees, foundEmployees);
    }

    @Test
    void findByName() {
        when(employeeRepository.findByFirstName(anyString())).thenReturn(employees);
        List<Employee> foundEmployees = employeeService.findByName("mockName");
        assertEquals(employees, foundEmployees);
    }
}