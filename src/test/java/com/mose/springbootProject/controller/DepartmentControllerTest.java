package com.mose.springbootProject.controller;


import com.mose.springbootProject.model.Department;
import com.mose.springbootProject.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
         department= Department.builder()
                 .deptId(1L)
                .deptName("Mechanical Engineering")
                .deptAddress("Asmara")
                .deptCode("ME22")
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
       Department inputdepartment= Department.builder()
                .deptName("Mechanical Engineering")
                .deptAddress("Asmara")
                .deptCode("ME22")
                .build();

        Mockito.when(departmentService.saveDepart(inputdepartment))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/dpt")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"deptName\":\"Physiology\",\n" +
                        "    \"deptAddress\": \"Massawa\",\n" +
                        "    \"deptCode\":\"PHS12\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAllDepartments() throws Exception {
        List<Department> departmentList = Arrays.asList(
                Department.builder().deptId(1L).deptName("Dept1").deptAddress("Addr1").deptCode("Code1").build(),
                Department.builder().deptId(2L).deptName("Dept2").deptAddress("Addr2").deptCode("Code2").build()

        );

        Mockito.when(departmentService.getAllDepartments()).thenReturn(departmentList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/dpt/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{}, {}]"));
        // Add the expected JSON content based on your departmentList
    }


    @Test
    void getDepartmentById() throws Exception {
        Mockito.when(departmentService.getDepartmentById(1L)).thenReturn(Optional.ofNullable(department));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/dpt/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"deptName\":\"Physiology\",\n" +
                                "    \"deptAddress\": \"Massawa\",\n" +
                                "    \"deptCode\":\"PHS12\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(get("/api/dpt/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deptName")
        .value(department.getDeptName()));
    }

    @Test
    void updateDepartment() throws Exception {
        Long deptId = 1L;
        Department updatedDepartment = Department.builder()
                .deptName("Updated Physiology")
                .deptAddress("Updated Massawa")
                .deptCode("UPD12")
                .build();

        Mockito.when(departmentService.updateDepartment(eq(deptId), any(Department.class)))
                .thenReturn(updatedDepartment);

        mockMvc.perform(put("/api/dpt/{deptId}", deptId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"deptName\":\"Updated Physiology\",\n" +
                                "    \"deptAddress\": \"Updated Massawa\",\n" +
                                "    \"deptCode\":\"UPD12\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deptName").value(updatedDepartment.getDeptName()))
                .andExpect(jsonPath("$.deptAddress").value(updatedDepartment.getDeptAddress()))
                .andExpect(jsonPath("$.deptCode").value(updatedDepartment.getDeptCode()));
    }

    @Test
    void getDepartmentByCode() throws Exception {
        String deptCode = "ME22";

        Mockito.when(departmentService.getDepartmentByCode(deptCode)).thenReturn(Optional.ofNullable(department));

        mockMvc.perform(get("/api/dpt/code/{deptCode}", deptCode)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deptName").value(department.getDeptName()))
                .andExpect(jsonPath("$.deptAddress").value(department.getDeptAddress()))
                .andExpect(jsonPath("$.deptCode").value(department.getDeptCode()));
    }

    @Test
    void deleteDepartmentById() throws Exception {
        Long deptId = 1L;

        mockMvc.perform(delete("/api/dpt/{deptId}", deptId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(departmentService, times(1)).deleteDepartment(deptId);
    }
}