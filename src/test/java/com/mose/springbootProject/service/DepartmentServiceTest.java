package com.mose.springbootProject.service;

import com.mose.springbootProject.model.Department;
import com.mose.springbootProject.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .deptCode("PHS02")
                .build();
        Mockito.when(departmentRepository.findByDeptCodeIgnoreCase("PHS02"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get valid code from database")
    @Disabled
    void whenValidDepartmentCode_thenDepartmentShouldFound() {
        String deptCode = "PHS02";
        Optional<Department> found = departmentService.getDepartmentByCode(deptCode);

        assertTrue(found.isPresent());
        Department department = found.orElseThrow(); // Extract the Department from Optional

        assertEquals(deptCode, department.getDeptCode());
    }


//    @BeforeEach
//    void setUp1() {
//        Department department = Department.builder()
//                .deptId(1L)
//                .build();
//        Mockito.when(departmentRepository.findById(1L))
//                .thenReturn(Optional.ofNullable(department));
//    }
//
//    @Test
//    void whenValidDepartmentId_thenDepartmentShouldFound() {
//        Long deptId = 1L;
//        Optional<Department> found1 = departmentRepository.findById(deptId);
//
//        assertTrue(found1.isPresent());
//        Department department = found1.orElseThrow();
//
//        assertEquals(deptId, department.getDeptId(), "Department ID should match");
//    }
}
