package com.mose.springbootProject.controller;


import com.mose.springbootProject.model.Department;
import com.mose.springbootProject.service.DepartmentServiceImp;
import exception.DepartmentNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger= LoggerFactory.getLogger(DepartmentController.class);
    @PostMapping
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department){
        logger.info("Department saved");
        return new ResponseEntity<>(deptService.saveDepart(department), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments(){
        logger.info("Inside get all departments of DepartmentController class");
       // logger.debug("debug DepartmentController class");
        return new ResponseEntity<>(deptService.getAllDepartments(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long deptId) {
        Optional<Department> departmentOptional = deptService.getDepartmentById(deptId);
           // method 1
//        return departmentOptional
//                .map(department -> new ResponseEntity<>(department, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        // method 2
        if(departmentOptional.isPresent()){
            Department department=departmentOptional.get();
            return new ResponseEntity<>(department,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
     //method 1
     /* public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id){
        return  new ResponseEntity<>(deptService.getDepartmentById(id),HttpStatus.OK);
    }*/

   /* public ResponseEntity<Department> updateDepartment(Department department, Long id){
        return new ResponseEntity<>(department.updateDepartment(),HttpStatus.OK);
    }*/

//@PutMapping("{id}")
/*public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long deptId,
 @RequestBody Department department) {
    try {
        Department updatedDepartment = deptService.updateDepartment(deptId, department);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    } catch (DepartmentNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}*/

    @PutMapping("{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long deptId, @RequestBody Department department) {
        try {
            Department updatedDepartment = deptService.updateDepartment(deptId, department);
            return ResponseEntity.ok(updatedDepartment);
        } catch (DepartmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); //a custom message
        }
    }

    @DeleteMapping("{id}")

public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long deptId) {
    try {
        deptService.deleteDepartment(deptId);
        return ResponseEntity.ok("Department deleted successfully");
    } catch (DepartmentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

//@GetMapping("/code/{code}")
//    public ResponseEntity<Department>getDepartmentByCode(@PathVariable("code") String deptCode){
//        Optional<Department>optionalDepartment=deptService.getDepartmentByCode(deptCode);
//        if(optionalDepartment.isPresent()){
//            Department department=optionalDepartment.get();
//            return new ResponseEntity<>(department,HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//}

    @GetMapping("/code/{code}")
    public ResponseEntity<Department> getDepartmentByCode(@PathVariable("code") String deptCode) {
        Department department = deptService.getDepartmentByCode(deptCode).orElse(null);

        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
