package com.odkor.myQnrProject.controllers;

import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.models.Employee;
import com.odkor.myQnrProject.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping({"/employees"})
    public String showEmployees(Model model) {

        log.info("Attempting to find all employees...");

        List<Employee> employees = employeeService.findAll();
        if(!employees.isEmpty()) {
            log.info("Attemp was successful, displaying results.");
            model.addAttribute("employees", employees);
            return "employee/allEmployees";
        }

        log.info("Attempt did not return any results, displaying error page.");
        model.addAttribute("notFound", "employees");
        return "error/notFound";

    }

    @GetMapping({"/employeesByDepartment/{departmentId}"})
    public String showEmployeesByDepartment(@PathVariable Long departmentId, Model model) {

        log.info(String.format("Attempting to find all employees belonging to department with id %o...", departmentId));

        List<Employee> employees = employeeService.findByDepartment(departmentId);
        model.addAttribute("employees", employees);
        if(!employees.isEmpty()) {
            log.info("Attempt was successful, displaying results.");
            model.addAttribute("department", employees.get(0).getDepartment().getName());
            return "employee/allEmployees";
        }

        log.info("Attempt did not return any results, displaying error page.");
        model.addAttribute("notFound", "employees");
        return "error/notFound";

    }

    @GetMapping({"/employee/{employeeId}"})
    public String showSingleEmployee(@PathVariable Long employeeId, Model model) {

        log.info(String.format("Attempting to find employee with id %o...", employeeId));

        Employee employee = employeeService.findById(employeeId);
        if(!isNull(employee)) {
            log.info("Attempt was successful, displaying results.");
            model.addAttribute("employee", employee);
            return "employee/singleEmployee";
        }

        log.info("Attempt did not return any results, displaying error page.");
        model.addAttribute("notFound", "employees");
        return "error/notFound";
    }
}
