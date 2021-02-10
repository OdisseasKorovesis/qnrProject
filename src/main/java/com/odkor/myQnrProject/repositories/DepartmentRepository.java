package com.odkor.myQnrProject.repositories;

import com.odkor.myQnrProject.models.Department;
import org.springframework.data.repository.CrudRepository;

import java.security.acl.Owner;
import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Department findByLocation(Long locationId);
    List<Department> findAllByLocation(Long locationId);
}
