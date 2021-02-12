package com.odkor.myQnrProject.controllers.restControllers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.odkor.myQnrProject.controllers.nonRestControllers.EmployeeController;
import com.odkor.myQnrProject.models.Department;
import com.odkor.myQnrProject.models.Employee;
import com.odkor.myQnrProject.models.Location;
import com.odkor.myQnrProject.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerRestTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeControllerRest employeeController;

    MockMvc mockMvc;
    List<Employee> employees;
    Employee employee;


    @BeforeEach
    void setUp() {
        employees = new ArrayList<>();
        employee = new Employee();
        employee.setId(1L);
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
        String EXPECTED =
                "<List>" +
                "   <item>" +
                "        <id>1</id>" +
                "        <lastName></lastName>" +
                "        <firstName></firstName>" +
                "        <job></job>" +
                "        <manager></manager>" +
                "        <hireDate></hireDate>" +
                "        <salary>0.0</salary>" +
                "        <commissions>0.0</commissions>" +
                "        <department>" +
                "            <id>1</id>" +
                "            <name></name>" +
                "            <location>" +
                "                <id>1</id>" +
                "                <name>mockLocation</name>" +
                "            </location>" +
                "        </department>" +
                "    </item>" +
                "</List>";

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(content().xml(EXPECTED));
    }

    @Test
    void showEmployees_Success_EmptyList() throws Exception {

        when(employeeService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/employees"))
                .andExpect(status().isNoContent());
    }

    @Test
    void showEmployees_Error() throws Exception {

        when(employeeService.findAll()).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void showEmployeesByDepartment_Success_NotEmptyList() throws Exception {

        when(employeeService.findByDepartment(anyLong())).thenReturn(employees);
        String EXPECTED =
                "<List>" +
                        "   <item>" +
                        "        <id>1</id>" +
                        "        <lastName></lastName>" +
                        "        <firstName></firstName>" +
                        "        <job></job>" +
                        "        <manager></manager>" +
                        "        <hireDate></hireDate>" +
                        "        <salary>0.0</salary>" +
                        "        <commissions>0.0</commissions>" +
                        "        <department>" +
                        "            <id>1</id>" +
                        "            <name></name>" +
                        "            <location>" +
                        "                <id>1</id>" +
                        "                <name>mockLocation</name>" +
                        "            </location>" +
                        "        </department>" +
                        "    </item>" +
                        "</List>";

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(content().xml(EXPECTED));
    }

    @Test
    void showEmployeesByDepartment_Success_EmptyList() throws Exception {

        when(employeeService.findByDepartment(anyLong())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void showEmployeesByDepartment_Error() throws Exception {

        when(employeeService.findByDepartment(anyLong())).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void showSingleEmployee_Success_NotNullEmployee() throws Exception {

        when(employeeService.findById(anyLong())).thenReturn(employee);

        String EXPECTED =
                "<Employee>" +
                "    <id>1</id>" +
                "    <lastName></lastName>" +
                "    <firstName></firstName>" +
                "    <job></job>" +
                "    <manager/>" +
                "    <hireDate></hireDate>" +
                "    <salary>0.0</salary>" +
                "    <department>" +
                "        <id>1</id>" +
                "        <name></name>" +
                "        <location>" +
                "            <id>1</id>" +
                "            <name>mockLocation</name>" +
                "        </location>" +
                "    </department>" +
                "    <commissions>0.0</commissions>" +
                "</Employee>";

        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(content().xml(EXPECTED));
    }

    @Test
    void showSingleEmployee_Success_NullEmployee() throws Exception {

        when(employeeService.findById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void showSingleEmployee_Error() throws Exception {

        when(employeeService.findById(anyLong())).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void showEmployeesByName_Success_NotEmptyList() throws Exception {
        when(employeeService.findByName(anyString())).thenReturn(employees);
        String EXPECTED =
                "<List>" +
                        "   <item>" +
                        "        <id>1</id>" +
                        "        <lastName></lastName>" +
                        "        <firstName></firstName>" +
                        "        <job></job>" +
                        "        <manager></manager>" +
                        "        <hireDate></hireDate>" +
                        "        <salary>0.0</salary>" +
                        "        <commissions>0.0</commissions>" +
                        "        <department>" +
                        "            <id>1</id>" +
                        "            <name></name>" +
                        "            <location>" +
                        "                <id>1</id>" +
                        "                <name>mockLocation</name>" +
                        "            </location>" +
                        "        </department>" +
                        "    </item>" +
                        "</List>";

        mockMvc.perform(get("/employees/byName?employeeName=abc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
                .andExpect(content().xml(EXPECTED));
    }

    @Test
    void showEmployeesByName_Success_EmptyList() throws Exception {

        when(employeeService.findByName(anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/employees/byName?employeeName=abc"))
                .andExpect(status().isNoContent());
    }

    @Test
    void showEmployeesByName_Error() throws Exception {

        when(employeeService.findByName(anyString())).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/employees/byName?employeeName=abc"))
                .andExpect(status().isInternalServerError());
    }
}