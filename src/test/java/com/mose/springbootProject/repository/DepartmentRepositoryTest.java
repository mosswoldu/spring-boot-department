package com.mose.springbootProject.repository;





import com.mose.springbootProject.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUP() {
        Department department = Department.builder()
                .deptName("Biology")
                .deptCode("BIO-02")
                .deptAddress("Keren")
                .build();
        entityManager.persist(department);
        entityManager.flush();
       // entityManager.remove(department);

        //entityManager.detach(department); // Remove entity from persistence context

    }


    @Test
    public void whenValidById_thenReturnDepartment(){
        Department  dept=departmentRepository.findById(2L).get();

        assertEquals(dept.getDeptName(),"Physiology");
    }

}