package com.devsenior.cdiaz.curso_restful.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.cdiaz.curso_restful.models.dto.CourseRequest;
import com.devsenior.cdiaz.curso_restful.models.dto.CourseResponse;
import com.devsenior.cdiaz.curso_restful.services.CourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/cursos")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAll();
    }

    @GetMapping("{id}")
    public CourseResponse getCourseById(@PathVariable("id") Long id) {
        return courseService.getOne(id);
    }

    @GetMapping("buscar")
    public List<CourseResponse> getCoursesByName(@RequestParam("nombre") String name) {
        return courseService.getByName(name);
    }

    @PostMapping
    public CourseResponse createCourse(@RequestBody CourseRequest course) {
        return courseService.create(course);
    }

    @PutMapping("{id}")
    public CourseResponse updateCourse(@PathVariable("id") Long id, @RequestBody CourseRequest course) {
        return courseService.update(id, course);
    }

    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable("id") Long id) {
        courseService.delete(id);
    }

}
