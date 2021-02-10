package com.odkor.myQnrProject.repositories;

import com.odkor.myQnrProject.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByDepartment(Long departmentId);
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
}
