package com.odkor.myQnrProject.controllers.restControllers;

import com.odkor.myQnrProject.Utils.Utils;
import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.models.Employee;
import com.odkor.myQnrProject.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@Profile("rest")
public class EmployeeControllerRest {

    private final EmployeeService employeeService;

    public EmployeeControllerRest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Employee>> showEmployees() {

        log.info("Attempting to find all employees...");

        try {
            List<Employee> employees = employeeService.findAll();

            if(employees.isEmpty()) {
                log.info("Attempt was successful, but returned empty results.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            log.info("Attempt was successful, list of employees was returned.");
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }catch (Exception ex) {
            String exceptionString = Utils.getExceptionStackTrace(ex);
            log.info("Attempt was unsuccessful due to exception: " + exceptionString);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/employees/{departmentId}", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Employee>> showEmployeesByDepartment(@PathVariable Long departmentId) {

        log.info(String.format("Attempting to find all employees belonging to department with id %o...", departmentId));

        try {
            List<Employee> employees = employeeService.findByDepartment(departmentId);

            if(employees.isEmpty()) {
                log.info("Attempt was successful, but returned empty results.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            log.info("Attempt was successful, list of employees was returned.");
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }catch (Exception ex) {
            String exceptionString = Utils.getExceptionStackTrace(ex);
            log.info("Attempt was unsuccessful due to exception: " + exceptionString);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/employee/{employeeId}", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Employee> showEmployeeById(@PathVariable Long employeeId) {

        log.info(String.format("Attempting to find employee with id %o...", employeeId));

        try {
           Employee employee = employeeService.findById(employeeId);

            if(isNull(employee)) {
                log.info("Attempt was successful, but did not return a result.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            log.info("Attempt was successful, employee was returned.");
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch (Exception ex) {
            String exceptionString = Utils.getExceptionStackTrace(ex);
            log.info("Attempt was unsuccessful due to exception: " + exceptionString);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/employees/byName", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Employee>> showEmployeesByName(@RequestParam String employeeName) {

        log.info(String.format("Attempting to find employee with name %s...", employeeName));

        try {
            List<Employee> employee = employeeService.findByName(employeeName);

            if(isNull(employee)) {
                log.info("Attempt was successful, but did not return a result.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            log.info("Attempt was successful, employee was returned.");
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch (Exception ex) {
            String exceptionString = Utils.getExceptionStackTrace(ex);
            log.info("Attempt was unsuccessful due to exception: " + exceptionString);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
