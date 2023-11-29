package com.mose.springbootProject.service;

import com.mose.springbootProject.model.Department;
import com.mose.springbootProject.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImp implements DepartmentService{
    private final DepartmentRepository depRepository;

    public Department saveDepart(Department dept){

        return  depRepository.save(dept);
    }

    public List<Department> getAllDepartments(){
        return depRepository.findAll();
    }

    public Optional<Department> getDepartmentById(long id) {
        return depRepository.findById(id);
    }
//    public Department getDepartmentById(Long id) {
//        return depRepository.findById(id).get();
//    }
  }
