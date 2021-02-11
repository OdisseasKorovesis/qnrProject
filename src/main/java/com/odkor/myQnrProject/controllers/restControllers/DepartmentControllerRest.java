package com.odkor.myQnrProject.controllers.restControllers;

import com.odkor.myQnrProject.Utils.Utils;
import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Profile("rest")
public class DepartmentControllerRest {

    private final DepartmentService departmentService;

    public DepartmentControllerRest(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/departments", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Department>> showDepartments() {

        log.info("Attempting to find all departments...");

        try {
            List<Department> departments = departmentService.findAll();

            if(departments.isEmpty()) {
                log.info("Attempt was successful, but returned empty results.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            log.info("Attempt was successful, list of departments was returned.");
            return new ResponseEntity<>(departments, HttpStatus.OK);
        }catch (Exception ex) {
            String exceptionString = Utils.getExceptionStackTrace(ex);
            log.info("Attempt was unsuccessful due to exception: " + exceptionString);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/departments/{locationId}", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Department>> showDepartmentsByLocation(@PathVariable Long locationId) {

        log.info(String.format("Attempting to find all departments belonging to location with id %o...", locationId));

        try {
            List<Department> departments = departmentService.findAllByLocation(locationId);

            if(departments.isEmpty()) {
                log.info("Attempt was successful, but returned empty results.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            log.info("Attempt was successful, list of departments was returned.");
            return new ResponseEntity<>(departments, HttpStatus.OK);
        }catch (Exception ex) {
            String exceptionString = Utils.getExceptionStackTrace(ex);
            log.info("Attempt was unsuccessful due to exception: " + exceptionString);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
