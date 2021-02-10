package com.odkor.myQnrProject.services;

import com.odkor.myQnrProject.models.Employee;
import com.odkor.myQnrProject.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;

    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findByDepartment(Long departmentId) {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findByDepartment(departmentId).forEach(employees::add);
        return employees;
    }

    @Override
    public List<Employee> findByName(String name) {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findByFirstName(name).forEach(employees::add);
        employeeRepository.findByLastName(name).forEach(employees::add);
        return employees;
    }
}
