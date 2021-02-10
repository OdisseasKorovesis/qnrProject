package com.odkor.myQnrProject.services;

import com.odkor.myQnrProject.models.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findById(Long id);
    List<Employee> findByDepartment(Long departmentId);
    List<Employee> findByName(String name);
}
