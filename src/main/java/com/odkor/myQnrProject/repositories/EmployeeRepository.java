package com.odkor.myQnrProject.repositories;

import com.odkor.myQnrProject.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employees WHERE department_id = ?1", nativeQuery = true)
    List<Employee> findByDepartment(Long departmentId);

    @Query(value = "SELECT * FROM employees WHERE first_name LIKE %?1%", nativeQuery = true)
    List<Employee> findByFirstName(String firstName);

    @Query(value = "SELECT * FROM employees WHERE last_name LIKE %?1%", nativeQuery = true)
    List<Employee> findByLastName(String lastName);
}
