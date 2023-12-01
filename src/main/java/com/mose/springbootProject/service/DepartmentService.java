package com.mose.springbootProject.service;


import com.mose.springbootProject.model.Department;
import com.mose.springbootProject.exception.DepartmentNotFoundExceptions;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    public Department saveDepart(Department dept);
    public List<Department> getAllDepartments();
    public Optional<Department> getDepartmentById(long id);
    public void deleteDepartment(Long deptId);

    public Department updateDepartment(Long deptId, Department department);

    public Optional<Department> getDepartmentByCode(String deptCode);
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundExceptions;
}
