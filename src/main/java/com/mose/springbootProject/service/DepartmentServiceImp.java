package com.mose.springbootProject.service;

import com.mose.springbootProject.model.Department;
import com.mose.springbootProject.repository.DepartmentRepository;
import com.mose.springbootProject.exception.DepartmentNotFoundException;
import com.mose.springbootProject.exception.DepartmentNotFoundExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
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

    public Department fetchDepartmentById(Long deptId) throws DepartmentNotFoundExceptions {
        Optional<Department> department =
                depRepository.findById(deptId);

        if(!department.isPresent()) {
            throw new DepartmentNotFoundExceptions("Department Not Available");
        }

        return  department.get();
    }

   @Override
   public void deleteDepartment(Long deptId) {
       Optional<Department> OptionalDepartment = depRepository.findById(deptId);

       if (OptionalDepartment.isPresent()) {
           depRepository.deleteById(deptId);
       } else {
           // Handle the case where the department with the given ID is not found
           throw new DepartmentNotFoundException("Department with ID " + deptId + " not found");
       }
   }

//    public Department updateDepartment(Long deptId,Department department) {
//        Department deptDB=depRepository.findById(deptId).get();
//        if(Objects.nonNull(department.getDeptName()) && !"".equalsIgnoreCase(department.getDeptName())){
//            deptDB.setDeptName(department.getDeptName());
//        }
//        if(Objects.nonNull(department.getDeptAddress()) && !"".equalsIgnoreCase(department.getDeptAddress())){
//            deptDB.setDeptAddress(department.getDeptAddress());
//        }
//        if(Objects.nonNull(department.getDeptCode()) && !"".equalsIgnoreCase(department.getDeptCode())){
//            deptDB.setDeptCode(department.getDeptCode());
//        }
//        return depRepository.save(deptDB);
//    }

    public Department updateDepartment(Long deptId, Department department) {
        Optional<Department> optionalDepartment = depRepository.findById(deptId);

        if (optionalDepartment.isPresent()) {
            Department deptDB = optionalDepartment.get();
            
            if (Objects.nonNull(department.getDeptName()) && !"".equalsIgnoreCase(department.getDeptName())) {
                deptDB.setDeptName(department.getDeptName());
            }
            if (Objects.nonNull(department.getDeptAddress()) && !"".equalsIgnoreCase(department.getDeptAddress())) {
                deptDB.setDeptAddress(department.getDeptAddress());
            }
            if (Objects.nonNull(department.getDeptCode()) && !"".equalsIgnoreCase(department.getDeptCode())) {
                deptDB.setDeptCode(department.getDeptCode());
            }

            return depRepository.save(deptDB);
        } else {
            // Throw a custom exception if the department is not found
            throw new DepartmentNotFoundException("Department with ID " + deptId + " not found");
        }
    }

    public Optional<Department> getDepartmentByCode(String deptCode) {
       // return Optional.ofNullable(depRepository.findByDeptCode(deptCode));
        return Optional.ofNullable(depRepository.findByDeptCodeIgnoreCase(deptCode));
    }
}
