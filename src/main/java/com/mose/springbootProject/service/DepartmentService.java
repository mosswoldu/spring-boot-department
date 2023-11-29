package com.mose.springbootProject.service;


import com.mose.springbootProject.model.Department;

import java.util.Optional;

public interface DepartmentService {
    public Optional<Department> getDepartmentById(long id);
    public void deleteDepartment(Long deptId);

    public Department updateDepartment(Long deptId, Department department);

    public Optional<Department> getDepartmentByCode(String deptCode);
}
