package com.gyanendra.jpqlandnativesql.repository;

import com.gyanendra.jpqlandnativesql.entities.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {
    @Query("from Student ")
    List<Student> findAllStudent();

    //fetch partial information

    @Query("select st.firstName, st.lastName from Student st")
    List<Object[]> findAllStudentsPartialData();

    @Query("from Student where firstName=:firstName") //named query parameter.
    List<Student> findAllStudentFirstName(@Param("firstName") String firstName);

    @Query("from Student where score>:min and score<:max")
    List<Student> findStudentsForGivenScore(@Param("min") int min, @Param("max") int max);

    @Modifying
    @Query("delete from Student where firstName= :firstName")
    void deleteStudentsByFirstName(@Param("firstName") String firstName);
}
