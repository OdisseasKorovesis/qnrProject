package com.odkor.myQnrProject.controllers;

import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.repositories.DepartmentRepository;
import com.odkor.myQnrProject.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.expression.Lists;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping({"/departments"})
    public String showDepartments(Model model) {

        log.info("Attempting to find all departments...");

        List<Department> departments = departmentService.findAll();
        if(!departments.isEmpty()) {
            log.info("Attempt was successful displaying results.");
            model.addAttribute("departments", departments);
            return "department/allDepartments";
        }

        log.info("Attempt did not return any results, displaying error page.");
        model.addAttribute("notFound", "departments");
        return "error/notFound";

    }

    @GetMapping({"/departmentsByLocation/{locationId}"})
    public String showDepartmentsByLocation(@PathVariable Long locationId, Model model) {

        log.info(String.format("Attempting to find all departments belonging to location with id %o...", locationId));

        List<Department> departments = departmentService.findAllByLocation(locationId);
        model.addAttribute("departments", departments);
        if(!isNull(departments)) {
            log.info("Attempt was successful displaying results.");
            model.addAttribute("location", departments.get(0).getLocation().getName());
            return "department/allDepartments";
        }

        log.info("Attempt did not return any results, displaying error page.");
        model.addAttribute("notFound", "department");
        return "error/notFound";

    }
}
