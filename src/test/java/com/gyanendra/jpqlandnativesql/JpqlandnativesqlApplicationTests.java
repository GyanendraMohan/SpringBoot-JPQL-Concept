package com.gyanendra.jpqlandnativesql;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.gyanendra.jpqlandnativesql.entities.Student;
import com.gyanendra.jpqlandnativesql.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class JpqlandnativesqlApplicationTests {

    @Autowired
    StudentRepository repository;

    @Test
    void testStudentCreate() {
        Student student = new Student();
        student.setFirstName("Gyanendra");
        student.setLastName("Mohan");
        student.setScore(100);

        Student student2 = new Student();
        student2.setFirstName("Divyansh");
        student2.setLastName("chandna");
        student2.setScore(95);

        repository.save(student);
        repository.save(student2);

    }
    //Pagination and sorting.
    @Test
    public void testFindAllStudents(){
        System.out.println(repository.findAllStudent(PageRequest.of(0, 5))); //remember this point.
        System.out.println(repository.findAllStudent(PageRequest.of(0, 5, Sort.Direction.DESC,"id"))); //remember this point.
    }

    @Test
    public void testFindAllStudentsPartialData(){
        List<Object[]> partialData = repository.findAllStudentsPartialData();
        for (Object[] objects: partialData){
            System.out.println(objects[0]);
            System.out.println(objects[1]);
        }
    }

    @Test
    public void testfindAllStudentFirstName(){
        System.out.println(repository.findAllStudentFirstName("Gyanendra"));
    }

    @Test
    public void testfindStudentsForGivenScore(){
        System.out.println(repository.findStudentsForGivenScore(90,101));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testDeleteStudentsByFirstName(){
        repository.deleteStudentsByFirstName("Divyansh");
    }

    @Test
    public void testFindAllStudentNQ(){
        System.out.println(repository.findAllStudentNQ());
    }

    @Test
    public void testFindByFirstNameNQ(){
        System.out.println(repository.findByFirstNameNQ("Gyanendra"));
    }

    @Test
    public void testFindAllStudentsPartialDataNQ(){
        List<Object[]> partialData = repository.findAllStudentsPartialDataNQ();
        for (Object[] objects: partialData){
            System.out.println(objects[0]);
            System.out.println(objects[1]);
        }
    }

}
