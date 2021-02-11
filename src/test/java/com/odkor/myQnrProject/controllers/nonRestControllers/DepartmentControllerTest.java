package com.odkor.myQnrProject.controllers.nonRestControllers;

import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    @Mock
    DepartmentService departmentService;

    @InjectMocks
    DepartmentController departmentController;

    MockMvc mockMvc;
    List<Department> departments;


    @BeforeEach
    void setUp() {

        departments = new ArrayList<>();
        Department department = new Department();
        Location location = new Location();
        location.setId(1L);
        location.setName("mockLocation");
        department.setId(1L);
        department.setLocation(location);
        departments.add(department);

        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    void showDepartments_Success_NotEmptyList() throws Exception{

        when(departmentService.findAll()).thenReturn(departments);

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("departments", hasSize(1)))
                .andExpect(view().name("department/allDepartments"));
    }

    @Test
    void showDepartments_Success_EmptyList() throws Exception {

        when(departmentService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("notFound", "departments"))
                .andExpect(view().name("error/notFound"));
    }

    @Test
    void showDepartmentsByLocation_Success_NotEmptyList() throws Exception {

        when(departmentService.findAllByLocation(anyLong())).thenReturn(departments);

        mockMvc.perform(get("/departmentsByLocation/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("departments", hasSize(1)))
                .andExpect(view().name("department/allDepartments"));
    }

    @Test
    void showDepartmentsByLocation_Success_EmptyList() throws Exception {

        when(departmentService.findAllByLocation(anyLong())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/departmentsByLocation/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("notFound", "department"))
                .andExpect(view().name("error/notFound"));
    }
}