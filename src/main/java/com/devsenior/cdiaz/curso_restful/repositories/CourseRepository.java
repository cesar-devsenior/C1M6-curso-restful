package com.devsenior.cdiaz.curso_restful.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsenior.cdiaz.curso_restful.models.entities.Course;
import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Long> {
    
    List<Course> findByNameIgnoringCaseContains(String name);

    @Query("SELECT c FROM Course c WHERE lower(c.name) like lower(:name)")
    List<Course> getAllByName(@Param("name") String name);

}
