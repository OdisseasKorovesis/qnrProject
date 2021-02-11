package com.odkor.myQnrProject.controllers.nonRestControllers;

import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.models.Employee;
import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    MockMvc mockMvc;
    List<Employee> employees;
    Employee employee;


    @BeforeEach
    void setUp() {
        employees = new ArrayList<>();
        employee = new Employee();
        Department department = new Department();
        Location location = new Location();
        location.setId(1L);
        location.setName("mockLocation");
        department.setId(1L);
        department.setLocation(location);
        employee.setDepartment(department);
        employees.add(employee);

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void showEmployees_Success_NotEmptyList() throws Exception {
        when(employeeService.findAll()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("employees", hasSize(1)))
                .andExpect(view().name("employee/allEmployees"));
    }

    @Test
    void showEmployees_Success_EmptyList() throws Exception {

        when(employeeService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("notFound", "employees"))
                .andExpect(view().name("error/notFound"));
    }

    @Test
    void showEmployeesByLocation_Success_NotEmptyList() throws Exception {

        when(employeeService.findByDepartment(anyLong())).thenReturn(employees);

        mockMvc.perform(get("/employeesByDepartment/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("employees", hasSize(1)))
                .andExpect(view().name("employee/allEmployees"));
    }

    @Test
    void showEmployeesByLocation_Success_EmptyList() throws Exception {

        when(employeeService.findByDepartment(anyLong())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/employeesByDepartment/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("notFound", "employees"))
                .andExpect(view().name("error/notFound"));
    }

    @Test
    void showSingleEmployee_Success_NotNullEmployee() throws Exception {

        when(employeeService.findById(anyLong())).thenReturn(employee);

        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("employee"))
                .andExpect(view().name("employee/singleEmployee"));
    }

    @Test
    void showSingleEmployee_Success_NullEmployee() throws Exception {

        when(employeeService.findById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("notFound", "employees"))
                .andExpect(view().name("error/notFound"));
    }
}