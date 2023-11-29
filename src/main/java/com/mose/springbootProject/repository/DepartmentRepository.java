package com.mose.springbootProject.repository;

import com.mose.springbootProject.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    public Department findByDeptCode(String deptCode);
}
