package com.mose.springbootProject.service;


import com.mose.springbootProject.model.Department;

public interface DepartmentService {
    public void deleteDepartment(Long deptId);

    public Department updateDepartment(Long deptId, Department department);
}
