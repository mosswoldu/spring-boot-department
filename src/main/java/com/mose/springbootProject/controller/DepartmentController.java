package com.mose.springbootProject.controller;


import com.mose.springbootProject.model.Department;
import com.mose.springbootProject.service.DepartmentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/dpt")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentServiceImp deptService;
    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){

        return new ResponseEntity<>(deptService.saveDepart(department), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments(){
        return new ResponseEntity<>(deptService.getAllDepartments(),HttpStatus.OK);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id) {
//        Optional<Department> departmentOptional = deptService.getDepartmentById(id);
//
//        return departmentOptional
//                .map(department -> new ResponseEntity<>(department, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id){
        return  new ResponseEntity<>(deptService.getDepartmentById(id),HttpStatus.OK);
    }


}
