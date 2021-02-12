package com.odkor.myQnrProject.controllers.restControllers;

import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerRestTest {

    @Mock
    DepartmentService departmentService;

    @InjectMocks
    DepartmentControllerRest departmentController;

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
    void showDepartments_Success_NotEmptyList() throws Exception {
        when(departmentService.findAll()).thenReturn(departments);

        String EXPECTED =
                "<List>" +
                "   <item>" +
                "       <id>1</id>" +
                "       <name></name>" +
                "       <location>" +
                "           <id>1</id>" +
                "           <name>mockLocation</name>" +
                "       </location>" +
                "   </item>" +
                "</List>";

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(content().xml(EXPECTED));
    }

    @Test
    void showDepartmentsByLocation_Success_EmptyList() throws Exception {
        when(departmentService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/departments"))
                .andExpect(status().isNoContent());
    }

    @Test
    void showDepartmentsByLocation_Error() throws Exception {
        when(departmentService.findAll()).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/departments"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void showDepartmentsByLocation_Success_NotEmptyList() throws Exception {
        when(departmentService.findAllByLocation(anyLong())).thenReturn(departments);

        String EXPECTED =
                "<List>" +
                        "   <item>" +
                        "       <id>1</id>" +
                        "       <name></name>" +
                        "       <location>" +
                        "           <id>1</id>" +
                        "           <name>mockLocation</name>" +
                        "       </location>" +
                        "   </item>" +
                        "</List>";

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(content().xml(EXPECTED));
    }

    @Test
    void showDepartments_Success_EmptyList() throws Exception {
        when(departmentService.findAllByLocation(anyLong())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void showDepartments_Error() throws Exception {
        when(departmentService.findAllByLocation(anyLong())).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isInternalServerError());
    }
}