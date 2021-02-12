package com.odkor.myQnrProject.repositories;

import com.odkor.myQnrProject.models.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.security.acl.Owner;
import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query(value = "SELECT * FROM departments WHERE location_id = ?1", nativeQuery = true)
    List<Department> findAllByLocation(Long locationId);
}
